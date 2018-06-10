package indi.smt.uno.crawler.photo;

import com.geccocrawler.gecco.GeccoEngine;
import indi.smt.uno.crawler.common.CommonConstacts;
import indi.smt.uno.crawler.service.page.handler.PaginationHandlerPipeline;
import indi.smt.uno.crawler.service.photo.list.handler.PhotoTableHandlerPipeline;

/**
 * PhotoApplicationTest
 * @author: Overload
 * @date: 2018/6/7 21:08
 * @version: 1.0
 */
public class PhotoApplicationTest {

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("---------开始爬取图片---------");
            // 图片列表 -> 详情
            // 获取列表
            GeccoEngine.create()
                    .classpath("indi.smt.uno.crawler.photo.list")
                    .start("http://www.9191ss.com/meituisiwa/index-3.html")
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
    }
}
