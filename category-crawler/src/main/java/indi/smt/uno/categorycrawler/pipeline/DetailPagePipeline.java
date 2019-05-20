package indi.smt.uno.categorycrawler.pipeline;

import com.alibaba.fastjson.JSON;
import com.geccocrawler.gecco.pipeline.Pipeline;
import indi.smt.uno.categorycrawler.common.CommonUtil;
import indi.smt.uno.categorycrawler.common.HttpUtil;
import indi.smt.uno.categorycrawler.entity.DetailPage;
import indi.smt.uno.categorycrawler.entity.VideoDesc;
import indi.smt.uno.categorycrawler.entity.VideoInfo;
import indi.smt.uno.categorycrawler.msg.MsgSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

/**
 * @author 無痕剑
 * @date 2019/4/14 16:43
 */
@Service("detailPagePipeline")
public class DetailPagePipeline implements Pipeline<DetailPage> {

	private final MsgSender msgSender;

	@Autowired
	public DetailPagePipeline(MsgSender msgSender) {
		this.msgSender = msgSender;
	}

	@Override
	public void process(DetailPage bean) {
		String title = null;
		String category = null;
		String date = null;
		for (VideoDesc videoDesc : bean.getVideoDescList()) {
			switch (videoDesc.getSpan()) {
				case "影片名称:":
					title = videoDesc.getDiv();
					break;
				case "影片分类:":
					category = videoDesc.getDiv();
					break;
				case "更新时间:":
					date = videoDesc.getDiv();
			}
		}

		String name = category + "-" + title;
		System.out.println("开始获取视频【" + name + "】下载连接");

		String requestUrl = bean.getRequest().getUrl();
		String pageId = requestUrl.substring(requestUrl.lastIndexOf("/") + 1, requestUrl.lastIndexOf("."));
		String src = bean.getScriptSrcList().stream()
				.filter(scriptSrc -> scriptSrc.contains(pageId))
				.findFirst()
				.orElse(null);
		if (StringUtils.isEmpty(src)) {
			return;
		}

		String jsUrl = CommonUtil.BASE_URL + src;
		String js;
		String videoUrl = null;
		try {
			js = HttpUtil.httpsGetForString(jsUrl);
		} catch (KeyManagementException | NoSuchAlgorithmException | IOException | KeyStoreException e) {
			System.out.println("**************************************");
			System.out.println("文件路径：" + jsUrl);
			System.out.println("获取JS文件发生异常------------------->");
			System.out.println(CommonUtil.exceptionString(e));
			System.out.println("**************************************");
			return;
		}

		String[] expressions = js.split(",");
		for (int i = expressions.length - 1; i >= 0; i--) {
			String expression = expressions[i];
			String[] kv = expression.split("=");
			if (kv[1].startsWith("unescape")) {
				String replace = kv[1].replace("');", "");
				String encodeUri = replace.substring(replace.indexOf("https"));
				try {
					videoUrl = URLDecoder.decode(encodeUri, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					System.out.println("**************************************");
					System.out.println("解码URI发生异常：");
					System.out.println(CommonUtil.exceptionString(e));
					System.out.println("**************************************");
				}
				break;
			}
		}

		if (StringUtils.isEmpty(videoUrl)) {
			return;
		}

		String datetime = LocalDate.now().getYear() + "-" + date + " 00:00:00";
		String msg = JSON.toJSONString(new VideoInfo(title, category, videoUrl, datetime));
		System.out.println("发送消息：" + name);
		msgSender.send(msg);
	}
}
