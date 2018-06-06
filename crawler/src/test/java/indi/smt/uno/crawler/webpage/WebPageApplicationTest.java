package indi.smt.uno.crawler.webpage;

import com.geccocrawler.gecco.GeccoEngine;

/**
 * @author SwordNoTrace
 * @date 2018/6/6 22:25
 */
public class WebPageApplicationTest {

	public static void main(String[] args) {
		GeccoEngine.create()
				.classpath("indi.smt.uno.crawler.model")
				.start("http://www.9191ss.com/")
				.interval(2000)
				.thread(1)
				.start();
	}
}
