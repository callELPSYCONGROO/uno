package indi.smt.uno.crawler.service.nav.model;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.spider.HtmlBean;
import indi.smt.uno.crawler.common.CommonConstacts;
import lombok.Data;

import java.util.List;

/**
 * @author SwordNoTrace
 * @date 2018/6/6 22:13
 */
@Data
@Gecco(matchUrl = CommonConstacts.BASE_URL, pipelines = {"webPageHandlerPipeline"})
public class WebPage implements HtmlBean {

	/**
	 * 图片
	 */
	@HtmlField(cssPath = "#wenzi > ul:nth-child(4)")
	private List<NavigationBar> photoList;

	/**
	 * 小说
	 */
	@HtmlField(cssPath = "#wenzi > ul:nth-child(5)")
	private List<NavigationBar> novelsList;
}
