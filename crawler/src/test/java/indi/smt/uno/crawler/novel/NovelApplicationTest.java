package indi.smt.uno.crawler.novel;

import com.geccocrawler.gecco.GeccoEngine;
import indi.smt.uno.crawler.common.CommonConstacts;
import indi.smt.uno.crawler.novel.list.handler.NovelTableHandlerPipeline;
import indi.smt.uno.crawler.page.handler.PaginationHandlerPipeline;

/**
 * NovelApplicationTest
 * @author: Overload
 * @date: 2018/6/7 21:08
 * @version: 1.0
 */
public class NovelApplicationTest {

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("---------开始爬取小说---------");
            // 小说列表 -> 详情
            // 获取列表
            GeccoEngine.create()
                    .classpath("indi.smt.uno.crawler.novel.list")
                    .start("http://www.9191ss.com/lingleikuwen/")
                    .interval(2000)
                    .thread(CommonConstacts.segmentation(PaginationHandlerPipeline.novelPaginationUrlList, 20))
                    .run();
            // 获取详情
            GeccoEngine.create()
                    .classpath("indi.smt.uno.crawler.novel.detail")
                    .start(NovelTableHandlerPipeline.novelDetailUrlList.toArray(new String[0]))
                    .interval(2000)
                    .thread(CommonConstacts.segmentation(PaginationHandlerPipeline.novelPaginationUrlList, 20))
                    .start();
            System.out.println("---------小说获取完成---------");
        }).start();
    }
}
