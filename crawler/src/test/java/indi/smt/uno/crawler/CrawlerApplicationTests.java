package indi.smt.uno.crawler;

import indi.smt.uno.crawler.config.MailHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlerApplicationTests {

	@Autowired
	private MailHelper mailHelper;

	@Test
	public void contextLoads() {

		mailHelper.send("测试", "完成");
	}

}
