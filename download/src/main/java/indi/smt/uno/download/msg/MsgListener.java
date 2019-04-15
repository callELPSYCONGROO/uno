package indi.smt.uno.download.msg;

import com.alibaba.fastjson.JSON;
import indi.smt.uno.download.common.CommonUtil;
import indi.smt.uno.download.common.HttpUtil;
import indi.smt.uno.download.entity.VideoInfo;
import indi.smt.uno.download.event.DownloadEvent;
import indi.smt.uno.download.event.DownloadEventPublishService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 無痕剑
 * @date 2019/4/14 20:19
 */
@Component
@RabbitListener(queues = CommonUtil.VIDEO_DOWNLOAD)
public class MsgListener {

	private final DownloadEventPublishService downloadEventPublishService;

	@Autowired
	public MsgListener(DownloadEventPublishService downloadEventPublishService) {
		this.downloadEventPublishService = downloadEventPublishService;
	}

	@RabbitHandler
	public void videoDownloadUrlListener(String message) {
		System.out.println("接受到消息：" + message);
		VideoInfo videoInfo = JSON.parseObject(message, VideoInfo.class);

		String download = videoInfo.getDownload();
		String info;
		try {
			info = HttpUtil.httpsGetForString(download);
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | IOException e) {
			throw new RuntimeException("------------>请求数据接口发生异常：\n" + CommonUtil.exceptionString(e));
		}

		if (StringUtils.isEmpty(info)) {
			throw new RuntimeException("----------->未获取到正确视频信息：" + download);
		}

		String segmentationUrl = null;
		for (String infoLine : info.split("\n")) {
			if (infoLine.startsWith("#")) {
				segmentationUrl = CommonUtil.BASE_URL + infoLine;
			}
		}
		if (StringUtils.isEmpty(segmentationUrl)) {
			throw new RuntimeException("----------->未获取到正确段落下载信息");
		}

		String segmentationInfo;
		try {
			segmentationInfo = HttpUtil.httpsGetForString(segmentationUrl);
		} catch (KeyManagementException | NoSuchAlgorithmException | IOException | KeyStoreException e) {
			throw new RuntimeException("------------>请求数据接口发生异常：\n" + CommonUtil.exceptionString(e));
		}

		List<String> segmentationList = Arrays.stream(segmentationInfo.split("\n"))
				// 去掉已#开头的行
				.filter(line -> !line.startsWith("#"))
				// 拼接成下载地址
				.map(line -> CommonUtil.BASE_URL + line)
				.collect(Collectors.toList());

		// 交给其他线程下载
		downloadEventPublishService.publish(new DownloadEvent(this, segmentationList));
	}
}
