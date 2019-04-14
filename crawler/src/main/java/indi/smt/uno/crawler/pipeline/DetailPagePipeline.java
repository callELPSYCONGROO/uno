package indi.smt.uno.crawler.pipeline;

import com.geccocrawler.gecco.pipeline.Pipeline;
import indi.smt.uno.crawler.common.CommonUtil;
import indi.smt.uno.crawler.common.HttpUtil;
import indi.smt.uno.crawler.entity.DetailPage;
import indi.smt.uno.crawler.entity.VideoDesc;
import indi.smt.uno.crawler.entity.VideoInfo;
import indi.smt.uno.crawler.msg.MsgSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * @author 無痕剑
 * @date 2019/4/14 16:43
 */
@Service("detailPagePipeline")
public class DetailPagePipeline implements Pipeline<DetailPage> {

	private MsgSender msgSender;

	@Autowired
	public DetailPagePipeline(MsgSender msgSender) {
		this.msgSender = msgSender;
	}

	@Override
	public void process(DetailPage bean) {
		String url = null;
		if (!CollectionUtils.isEmpty(bean.getScriptSrcList())) {
			for (String src : bean.getScriptSrcList()) {
				if (src.matches(CommonUtil.SCRIPT_SRC_REGEX)) {
					String js;
					String jsUrl = CommonUtil.BASE_URL + src;
					try {
						js = HttpUtil.httpsGetForString(jsUrl);
					} catch (KeyManagementException | NoSuchAlgorithmException | IOException | KeyStoreException e) {
						System.out.println("**************************************");
						System.out.println("文件路径：" + jsUrl);
						System.out.println("获取JS文件发生异常------------------->");
						System.out.println(CommonUtil.exceptionString(e));
						System.out.println("**************************************");
						continue;
					}

					String[] expressions = js.split(",");
					for (int i = expressions.length - 1; i >= 0; i--) {
						String expression = expressions[i];
						String[] kv = expression.split("=");
						if (kv[1].startsWith("unescape")) {
							String replace = kv[1].replace("');", "");
							String encodeUri = replace.substring(replace.indexOf("https"));
							try {
								url = URLDecoder.decode(encodeUri, "UTF-8");
							} catch (UnsupportedEncodingException e) {
								System.out.println("**************************************");
								System.out.println("解码URI发生异常：");
								System.out.println(CommonUtil.exceptionString(e));
								System.out.println("**************************************");
							}
							break;
						}
					}
				}
			}
		}

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
		VideoInfo videoInfo = new VideoInfo(title, category, url, date);
		System.out.println("发送消息：" + videoInfo);
		msgSender.send(videoInfo);
	}
}
