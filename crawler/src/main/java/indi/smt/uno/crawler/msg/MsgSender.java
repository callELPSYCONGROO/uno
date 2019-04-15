package indi.smt.uno.crawler.msg;

import indi.smt.uno.crawler.common.CommonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 無痕剑
 * @date 2019/4/14 20:10
 */
@Component
public class MsgSender {

	private AmqpTemplate amqpTemplate;

	@Autowired
	public MsgSender(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

	public void send(String msg) {
		amqpTemplate.convertAndSend(CommonUtil.VIDEO_TOPIC_EXCHANGE, CommonUtil.ROUTING_KEY, msg);
	}
}
