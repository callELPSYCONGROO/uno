package indi.smt.uno.crawler.service.nav.handler;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.spider.HrefBean;
import indi.smt.uno.crawler.service.nav.model.NavigationBar;
import indi.smt.uno.crawler.service.nav.model.WebPage;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类导航URL处理
 * @author SwordNoTrace
 * @date 2018/6/6 22:04
 */
@PipelineName("webPageHandlerPipeline")
public class WebPageHandlerPipeline implements Pipeline<WebPage> {

	/** 图片 */
	public static List<String> photoCategories = new ArrayList<>();

	/** 小说 */
	public static List<String> novelsCategories = new ArrayList<>();

	@Override
	public void process(WebPage bean) {
		for (NavigationBar nb : bean.getNovelsList()) {
			for (HrefBean hb : nb.getChilrenNav()) {
				novelsCategories.add(hb.getUrl());
			}
		}
		for (NavigationBar nb : bean.getPhotoList()) {
			for (HrefBean hb : nb.getChilrenNav()) {
				photoCategories.add(hb.getUrl());
			}
		}
	}
}
