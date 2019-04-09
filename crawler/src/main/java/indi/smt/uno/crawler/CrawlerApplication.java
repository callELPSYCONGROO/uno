package indi.smt.uno.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 扫描com.geccocrawler.gecco.spring，自动注入springPipelineFactory和consolePipeline
 */
@SpringBootApplication(scanBasePackages = {"com.geccocrawler.gecco.spring", "indi.smt.uno.crawler"})
public class CrawlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrawlerApplication.class, args);
	}
}
