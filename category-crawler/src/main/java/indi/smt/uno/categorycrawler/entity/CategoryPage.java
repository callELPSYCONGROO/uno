package indi.smt.uno.categorycrawler.entity;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import indi.smt.uno.categorycrawler.common.CommonUtil;
import lombok.Data;

import java.util.List;

/**
 * @author 無痕剑
 * @date 2019/5/14 0:33
 */
@Data
@Gecco(matchUrl = CommonUtil.CATEGORY_URL, pipelines = {"categoryPagePipeline", "consolePipeline"})
public class CategoryPage implements HtmlBean {

	/** 获取请求，创建派生请求 */
	@Request
	private HttpRequest request;

	@Href
	@HtmlField(cssPath = ".kt_imgrc")
	private List<String> detailHrefList;
}
