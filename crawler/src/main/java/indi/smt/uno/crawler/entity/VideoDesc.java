package indi.smt.uno.crawler.entity;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;

/**
 * @author 無痕剑
 * @date 2019/4/14 21:19
 */
@Data
public class VideoDesc implements HtmlBean {

	@Text
	@HtmlField(cssPath = ".title")
	private String span;

	@Text
	@HtmlField(cssPath = ".desc-link")
	private String div;
}
