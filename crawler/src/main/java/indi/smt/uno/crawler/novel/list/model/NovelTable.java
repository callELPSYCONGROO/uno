package indi.smt.uno.crawler.novel.list.model;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.spider.HrefBean;
import com.geccocrawler.gecco.spider.HtmlBean;
import indi.smt.uno.crawler.common.CommonConstacts;
import lombok.Data;

import java.util.List;

/**
 * @author SwordNoTrace
 * @date 2018/6/6 23:56
 */
@Data
@Gecco(matchUrl = CommonConstacts.BASE_URL + "{type}/{index}", pipelines = "novelTableHandlerPipeline")
public class NovelTable implements HtmlBean {

	@HtmlField(cssPath = "#main ul table tbody tr td:nth-child(1)")
	private List<HrefBean> novelDetailList;
}
