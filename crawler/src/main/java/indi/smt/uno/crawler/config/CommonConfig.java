package indi.smt.uno.crawler.config;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.spring.SpringGeccoEngine;
import indi.smt.uno.crawler.common.CommonUtil;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
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
	public SpringGeccoEngine springGeccoEngine() {
		return new SpringGeccoEngine() {
			@Override
			public void init() {
				GeccoEngine.create()
						.pipelineFactory(springPipelineFactory)
						.classpath("indi.smt.uno.crawler.entity")
						.start(CommonUtil.BASE_URL)
						.thread(threadCount)
						.interval(interval)
						.start();
			}
		};
	}

	@Bean("fanoutDownload")
	public Queue fanoutDownload() {
		return new Queue(CommonUtil.FANOUT_DOWNLOAD);
	}

	@Bean("fanoutSave")
	public Queue fanoutSave() {
		return new Queue(CommonUtil.FANOUT_SAVE);
	}

	@Bean("fanoutExchange")
	FanoutExchange fanoutExchange() {
		return new FanoutExchange(CommonUtil.FANOUTEXCHANGE);
	}

	@Bean
	Binding bindingExchangeFanoutSave(Queue fanoutSave,
	                         FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(fanoutSave).to(fanoutExchange);
	}

	@Bean
	Binding bindingExchangeFanoutDownload(Queue fanoutDownload,
	                         FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(fanoutDownload).to(fanoutExchange);
	}

}
