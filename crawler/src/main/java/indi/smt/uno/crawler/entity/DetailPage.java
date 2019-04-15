package indi.smt.uno.crawler.entity;

import com.geccocrawler.gecco.annotation.Attr;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;

import java.util.List;

/**
 * @author 無痕剑
 * @date 2019/4/14 16:17
 */
@Data
@Gecco(matchUrl = "https://usa-10.us/vodplayhtml/{pageIdHtml}", pipelines = {"detailPagePipeline", "consolePipeline"})
public class DetailPage implements HtmlBean {

	@Attr("src")
	@HtmlField(cssPath = "script")
	private List<String> scriptSrcList;

	@HtmlField(cssPath = ".row")
	private List<VideoDesc> videoDescList;
}
