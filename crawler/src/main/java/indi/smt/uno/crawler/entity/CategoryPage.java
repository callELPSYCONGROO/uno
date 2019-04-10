package indi.smt.uno.crawler.entity;

import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HrefBean;
import com.geccocrawler.gecco.spider.HtmlBean;
import indi.smt.uno.crawler.common.CommonUtil;
import lombok.Data;

import java.util.List;

/**
 * 分类页面
 * @author 無痕剑
 * @date 2019/4/9 23:50
 */
@Data
@Gecco(matchUrl = CommonUtil.BASE_URL + CommonUtil.NAVI_CATEGORY_MATCH, pipelines = {"categoryPagePipeline", "consolePipeline"})
public class CategoryPage implements HtmlBean {

	/** 分类[-页数] */
	@RequestParameter("pageId")
	private String pageId;

	/** 当前分页的视频页面 */
	@HtmlField(cssPath = ".thumb-content a")
	private List<HrefBean> vodhtmlList;

	/** 分页信息 */
	@Text
	@HtmlField(cssPath = ".pagination")
	private List<String> pagination;

	@Attr("onclick")
	@HtmlField(cssPath = ".pagebtn")
	private String pagebtn;

	/** 获取请求，创建派生请求 */
	@Request
	private HttpRequest request;
}
