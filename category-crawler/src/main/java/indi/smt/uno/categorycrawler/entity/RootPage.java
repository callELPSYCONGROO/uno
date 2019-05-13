package indi.smt.uno.categorycrawler.entity;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import indi.smt.uno.categorycrawler.common.CommonUtil;
import lombok.Data;

/**
 * @author 無痕剑
 * @date 2019/5/14 0:44
 */
@Data
@Gecco(matchUrl = CommonUtil.BASE_URL, pipelines = {"rootPagePipeline", "consolePipeline"})
public class RootPage implements HtmlBean {

	/** 获取请求，创建派生请求 */
	@Request
	private HttpRequest request;
}
