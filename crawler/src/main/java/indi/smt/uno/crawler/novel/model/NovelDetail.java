package indi.smt.uno.crawler.novel.model;

import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.spider.HrefBean;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;

/**
 * @author SwordNoTrace
 * @date 2018/6/7 0:05
 */
@Data
public class NovelDetail implements HtmlBean {

	@Href
	@HtmlField(cssPath = "a")
	private HrefBean a;
}
