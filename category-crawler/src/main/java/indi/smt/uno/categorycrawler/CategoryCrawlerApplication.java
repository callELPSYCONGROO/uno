package indi.smt.uno.categorycrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.geccocrawler.gecco.spring", "indi.smt.uno.categorycrawler"})
public class CategoryCrawlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoryCrawlerApplication.class, args);
	}

}
