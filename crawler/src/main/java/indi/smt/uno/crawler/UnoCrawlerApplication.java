package indi.smt.uno.crawler;

import com.geccocrawler.gecco.GeccoEngine;
import indi.smt.uno.crawler.common.CommonConstacts;

/**
 * 爬虫启动类
 */
public class UnoCrawlerApplication {

	public static void main(String[] args) {
		// 首页->分类导航栏
		GeccoEngine.create()
				.classpath("indi.smt.uno.crawler.nav")
				.start(CommonConstacts.BASE_URL)
				.interval(2000)
//                .loop(true)
				.run(); // 使用阻塞线程，将所有分类获取完成之后再开始下一步

		// 分页信息
		GeccoEngine.create()
				.classpath("indi.smt.uno.crawler.page")
				.start("http://www.9191ss.com/dongmanHtu/")
				.interval(2000)
//                .loop(true)
				.run(); // 使用阻塞线程，将所有分类获取完成之后再开始下一步

		// 分类列表->详情
		// 图片
//		GeccoEngine.create()
//				.classpath("indi.smt.uno.crawler.photo")
//				.start(WebPageHandlerPipeline.photoCategories.toArray(new String[0]))
//				.interval(2000)
//				.thread(3)
//				.start();

		// 小说
//		GeccoEngine.create()
//				.classpath("indi.smt.uno.crawler.novel")
//				.start(WebPageHandlerPipeline.novelsCategories.toArray(new String[0]))
//				.interval(2000)
//				.thread(3)
//				.start();
	}
}
