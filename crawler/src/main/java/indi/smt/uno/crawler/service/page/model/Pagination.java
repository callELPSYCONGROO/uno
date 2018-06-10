package indi.smt.uno.crawler.service.page.model;

import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import indi.smt.uno.crawler.common.CommonConstacts;
import lombok.Data;

/**
 * 获取页码信息
 * @author SwordNoTrace
 * @date 2018/6/7 0:29
 */
@Data
@Gecco(matchUrl = CommonConstacts.BASE_URL + "{type}/", pipelines = "paginationHandlerPipeline")
public class Pagination implements HtmlBean {

	@Request
	private HttpRequest httpRequest;

	@RequestParameter("type")
	private String type;

	@Text
	@HtmlField(cssPath = "#main .pagination .pageinfo strong:nth-child(1)")
	private int totalPageNum;
}
