package indi.smt.uno.save.msg;

import indi.smt.uno.save.common.CommonUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 無痕剑
 * @date 2019/4/14 20:19
 */
@Component
@RabbitListener(queues = CommonUtil.VIDEO_SAVE)
public class MsgListener {

	@RabbitHandler
	public void videoDownloadUrlListener(String message) {
		System.out.println("接受到消息：" + message);
	}
}
