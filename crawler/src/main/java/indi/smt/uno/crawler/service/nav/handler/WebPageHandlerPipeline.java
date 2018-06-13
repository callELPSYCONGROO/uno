package indi.smt.uno.crawler.service.nav.handler;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.spider.HrefBean;
import indi.smt.uno.crawler.common.CommonConstacts;
import indi.smt.uno.crawler.dao.CategoryMapper;
import indi.smt.uno.crawler.dao.SqlSessionUtil;
import indi.smt.uno.crawler.entity.Category;
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
		List<Category> categoryList = new ArrayList<>();
		// 将标签分类装入列表，给页面解析处理类使用
		for (NavigationBar nb : bean.getNovelsList()) {
			for (HrefBean hb : nb.getChilrenNav()) {
				categoryList.add(come2Category(nb.getParentNav().getTitle(), hb.getTitle()));
				novelsCategories.add(hb.getUrl());
			}
		}
		for (NavigationBar nb : bean.getPhotoList()) {
			for (HrefBean hb : nb.getChilrenNav()) {
				categoryList.add(come2Category(nb.getParentNav().getTitle(), hb.getTitle()));
				photoCategories.add(hb.getUrl());
			}
		}
		// 将标签分类持久化
		try (SqlSessionUtil sqlSessionUtil = new SqlSessionUtil()) {
			sqlSessionUtil.getDao(CategoryMapper.class).insertBatch(categoryList);
		} catch (Exception e) {
			System.out.println("发生异常：" + e.getMessage());
		}
	}

	private Category come2Category(String parentName, String cateName) {
		Category category = new Category();
		category.setDomain(CommonConstacts.DOMAIN);
		category.setParentName(parentName);
		category.setCateName(cateName);
		return category;
	}
}
