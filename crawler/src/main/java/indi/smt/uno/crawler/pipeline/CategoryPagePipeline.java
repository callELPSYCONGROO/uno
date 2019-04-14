package indi.smt.uno.crawler.pipeline;

import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext;
import com.geccocrawler.gecco.spider.HrefBean;
import indi.smt.uno.crawler.common.CommonUtil;
import indi.smt.uno.crawler.entity.CategoryPage;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.List;

/**
 * 分类页面处理管道
 * @author 無痕剑
 * @date 2019/4/9 23:53
 */
@Service("categoryPagePipeline")
public class CategoryPagePipeline implements Pipeline<CategoryPage> {

	@Override
	public void process(CategoryPage bean) {
		// 下一页子查询
		List<String> pagination = bean.getPagination();
		HttpRequest request = bean.getRequest();
		String pagebtn = bean.getPagebtn();
		if (!CollectionUtils.isEmpty(pagination)) {
			// 解析分页信息，获取下一页
			for (String paginationInfo : pagination) {
				String[] paginations = CommonUtil.matchPagination(paginationInfo);
				if (paginations == null) {
					continue;
				}
				// 当前页 < 总页数，则有下一页
				if (Integer.valueOf(paginations[1]) < Integer.valueOf(paginations[2])) {
					// 生成下一页URL
					String nextUri = CommonUtil.nextUri(pagebtn, Integer.valueOf(paginations[1]) + 1);
					if (StringUtils.isEmpty(nextUri)) {
						continue;
					}
					String nextUrl = CommonUtil.BASE_URL + nextUri;
					DeriveSchedulerContext.into(request.subRequest(nextUrl));
				}
			}
		}
		// 视频详情页查询
		List<HrefBean> vodhtmlList = bean.getVodhtmlList();
		if (!CollectionUtils.isEmpty(vodhtmlList)) {
			vodhtmlList.forEach(hrefBean -> {
				// 获取的详情页转换为最终地址
				String detailUrl = hrefBean.getUrl();
				String pageId = detailUrl.substring(detailUrl.lastIndexOf("/") + 1, detailUrl.lastIndexOf("."));
				String detailFinalUrl = MessageFormat.format(CommonUtil.DETAIL_URL_REGEX, pageId, pageId);
				DeriveSchedulerContext.into(request.subRequest(detailFinalUrl));
			});
		}
	}
}
