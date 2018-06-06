package indi.smt.uno.crawler.nav.model;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.spider.HrefBean;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;

import java.util.List;

/**
 * @author SwordNoTrace
 * @date 2018/6/6 21:23
 */
@Data
public class NavigationBar implements HtmlBean {

	/**
	 * 父目录
	 */
	@HtmlField(cssPath = "li[class=active]")
	private HrefBean parentNav;

	/**
	 * 子目录
	 */
	@HtmlField(cssPath = "li:not(.active)")
	private List<HrefBean> chilrenNav;
}
