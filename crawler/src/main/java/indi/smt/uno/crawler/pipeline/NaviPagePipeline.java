package indi.smt.uno.crawler.pipeline;

import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext;
import com.geccocrawler.gecco.spider.HrefBean;
import indi.smt.uno.crawler.common.CommonUtil;
import indi.smt.uno.crawler.entity.NaviPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 导航菜单处理管道
 * @author 無痕剑
 * @date 2019/4/9 23:20
 */
@Slf4j
@Service("naviPagePipeline")
public class NaviPagePipeline implements Pipeline<NaviPage> {

	@Override
	public void process(NaviPage bean) {
		List<HrefBean> categoryList = bean.getCategoryList();
		if (CollectionUtils.isEmpty(categoryList)) {
			return;
		}
		// 当前请求
		HttpRequest currentRequest = bean.getRequest();

		categoryList.stream()
				// 路径规则匹配
				.filter(category -> {
					String uri = category.getUrl();
					return !StringUtils.isEmpty(uri)
							&& uri.matches(CommonUtil.BASE_URL + CommonUtil.NAVI_CATEGORY_REGEX);
				})
				// 增加派生请求
				.forEach(category -> DeriveSchedulerContext.into(currentRequest.subRequest(category.getUrl())));
	}
}
