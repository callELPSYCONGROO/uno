package indi.smt.uno.crawler.entity;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HrefBean;
import com.geccocrawler.gecco.spider.HtmlBean;
import indi.smt.uno.crawler.common.CommonUtil;
import lombok.Data;

import java.util.List;

/**
 * 获取导航栏
 * @author 無痕剑
 * @date 2019/4/9 22:55
 */
@Data
@Gecco(matchUrl = CommonUtil.BASE_URL, pipelines = {"naviPagePipeline", "consolePipeline"})
public class NaviPage implements HtmlBean {

	/** 分类菜单 */
	@HtmlField(cssPath = ".responsive-menu ul li a")
	private List<HrefBean> categoryList;

	/** 获取请求，创建派生请求 */
	@Request
	private HttpRequest request;
}
