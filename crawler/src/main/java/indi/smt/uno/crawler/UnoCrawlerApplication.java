package indi.smt.uno.crawler;

import com.geccocrawler.gecco.GeccoEngine;
import indi.smt.uno.crawler.common.CommonConstacts;
import indi.smt.uno.crawler.nav.handler.WebPageHandlerPipeline;
import indi.smt.uno.crawler.novel.list.handler.NovelTableHandlerPipeline;
import indi.smt.uno.crawler.page.handler.PaginationHandlerPipeline;
import indi.smt.uno.crawler.photo.list.handler.PhotoTableHandlerPipeline;

/**
 * 爬虫启动类
 */
public class UnoCrawlerApplication {

	public static void main(String[] args) {
		System.out.println("---------开始爬取网页---------");
		System.out.println("---------获取分类导航URL---------");
		// 首页->分类导航栏
		GeccoEngine.create()
				.classpath("indi.smt.uno.crawler.nav")
				.start(CommonConstacts.BASE_URL)
				.interval(2000)
				.run(); // 使用阻塞线程，将所有分类获取完成之后再开始下一步
		System.out.println("---------分类导航URL完成---------");
		System.out.println("---------获取分页信息URL---------");
		// 分页信息
		GeccoEngine.create()
				.classpath("indi.smt.uno.crawler.page")
				.start(WebPageHandlerPipeline.photoCategories.toArray(new String[0]))
				.interval(2000)
				.thread(CommonConstacts.segmentation(WebPageHandlerPipeline.photoCategories, 20))
				.run(); // 使用阻塞线程，将所有分类获取完成之后再开始下一步
		System.out.println("---------分页信息URL完成---------");
		// 分别爬取图片和小说
		new Thread(() -> {
			System.out.println("---------开始爬取图片---------");
			// 图片列表 -> 详情
			// 获取列表
			GeccoEngine.create()
					.classpath("indi.smt.uno.crawler.photo.list")
					.start(PaginationHandlerPipeline.photoPaginationUrlList.toArray(new String[0]))
					.interval(2000)
					.thread(CommonConstacts.segmentation(PaginationHandlerPipeline.photoPaginationUrlList, 20))
					.run();
			// 获取详情
			GeccoEngine.create()
					.classpath("indi.smt.uno.crawler.photo.detail")
					.start(PhotoTableHandlerPipeline.photoDetailUrlList.toArray(new String[0]))
					.interval(2000)
					.thread(CommonConstacts.segmentation(PhotoTableHandlerPipeline.photoDetailUrlList, 20))
					.start();
			System.out.println("---------图片获取完成---------");
		}).start();

		new Thread(() -> {
			System.out.println("---------开始爬取小说---------");
			// 小说列表 -> 详情
			// 获取列表
			GeccoEngine.create()
					.classpath("indi.smt.uno.crawler.novel.list")
					.start(PaginationHandlerPipeline.novelPaginationUrlList.toArray(new String[0]))
					.interval(2000)
					.thread(CommonConstacts.segmentation(PaginationHandlerPipeline.novelPaginationUrlList, 20))
					.run();
			// 获取详情
			GeccoEngine.create()
					.classpath("indi.smt.uno.crawler.novel.detail")
					.start(NovelTableHandlerPipeline.novelDetailUrlList.toArray(new String[0]))
					.interval(2000)
					.thread(CommonConstacts.segmentation(NovelTableHandlerPipeline.novelDetailUrlList, 20))
					.start();
			System.out.println("---------小说获取完成---------");
		}).start();
	}
}
