package indi.smt.uno.download.msg;

import indi.smt.uno.download.common.CommonUtil;
import indi.smt.uno.download.entity.VideoInfo;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 無痕剑
 * @date 2019/4/14 20:19
 */
@Component
@RabbitListener(queues = CommonUtil.VIDEO_DOWNLOAD_URL_QUEUE)
public class MsgListener {

	@RabbitHandler
	public void videoDownloadUrlListener(VideoInfo message) {
		System.out.println("接受到消息：" + message);
	}
}
