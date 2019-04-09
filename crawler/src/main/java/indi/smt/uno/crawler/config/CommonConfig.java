package indi.smt.uno.crawler.config;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.spring.SpringGeccoEngine;
import indi.smt.uno.crawler.common.CommonConstacts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 無痕剑
 * @date 2019/4/10 0:31
 */
@Configuration
public class CommonConfig {

	@Bean
	public SpringGeccoEngine springGeccoEngine() {
		return new SpringGeccoEngine() {
			@Override
			public void init() {
				GeccoEngine.create()
						.pipelineFactory(springPipelineFactory)
						.classpath("indi.smt.uno.crawler.entity")
						.start(CommonConstacts.BASE_URL)
						.interval(1000)
						.start();
			}
		};
	}
}
