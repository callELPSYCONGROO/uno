package indi.smt.uno.categorycrawler.config;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.spring.SpringGeccoEngine;
import indi.smt.uno.categorycrawler.common.CommonUtil;
import indi.smt.uno.categorycrawler.event.GeccoStatusListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 無痕剑
 * @date 2019/4/10 0:31
 */
@Configuration
public class CommonConfig {

	@Value("${gecco.thread}")
	private int threadCount;

	@Value("${gecco.interval}")
	private int interval;

	@Bean
	public SpringGeccoEngine springGeccoEngine(GeccoStatusListener geccoStatusListener) {
		return new SpringGeccoEngine() {
			@Override
			public void init() {
				GeccoEngine.create()
						.pipelineFactory(springPipelineFactory)
						.classpath("indi.smt.uno.categorycrawler.entity")
						.start(CommonUtil.BASE_URL)
						.thread(threadCount)
						.interval(interval)
						.setEventListener(geccoStatusListener)
						.loop(false)
						.start();
			}
		};
	}

	@Bean("downloadQueue")
	public Queue downloadQueue() {
		return new Queue(CommonUtil.VIDEO_DOWNLOAD);
	}

	@Bean("saveQueue")
	public Queue saveQueue() {
		return new Queue(CommonUtil.VIDEO_SAVE);
	}

	@Bean("videoTopicExchange")
	TopicExchange videoTopicExchange() {
		return new TopicExchange(CommonUtil.VIDEO_TOPIC_EXCHANGE);
	}

	@Bean
	Binding bindingExchangeSave(Queue saveQueue,
	                            TopicExchange videoTopicExchange) {
		return BindingBuilder.bind(saveQueue).to(videoTopicExchange).with(CommonUtil.ROUTING_KEY);
	}

	@Bean
	Binding bindingExchangeDownload(Queue downloadQueue,
	                                TopicExchange videoTopicExchange) {
		return BindingBuilder.bind(downloadQueue).to(videoTopicExchange).with(CommonUtil.ROUTING_KEY);
	}

}
