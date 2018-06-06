package indi.smt.uno.crawler.novel.model;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.spider.HtmlBean;
import indi.smt.uno.crawler.common.CommonConstacts;
import lombok.Data;

import java.util.List;

/**
 * @author SwordNoTrace
 * @date 2018/6/6 23:56
 */
@Data
@Gecco(matchUrl = CommonConstacts.BASE_URL + "{type}/{title}/", pipelines = "novelHandlerPipeline")
public class NovelTable implements HtmlBean {

	@RequestParameter("type")
	private String type;

	@RequestParameter("title")
	private String title;

	@HtmlField(cssPath = "#main ul table")
	private List<NovelDetail> novelDetailList;
}
