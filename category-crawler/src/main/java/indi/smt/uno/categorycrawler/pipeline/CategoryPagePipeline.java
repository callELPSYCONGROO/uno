package indi.smt.uno.categorycrawler.pipeline;

import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext;
import indi.smt.uno.categorycrawler.common.CommonUtil;
import indi.smt.uno.categorycrawler.entity.CategoryPage;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.List;

/**
 * @author 無痕剑
 * @date 2019/5/14 0:42
 */
@Service("categoryPagePipeline")
public class CategoryPagePipeline implements Pipeline<CategoryPage> {

	@Override
	public void process(CategoryPage bean) {
		List<String> detailHrefList = bean.getDetailHrefList();
		if (CollectionUtils.isEmpty(detailHrefList)) {
			return;
		}
		HttpRequest request = bean.getRequest();
		detailHrefList.forEach(href -> {
			String categoryId = href.substring(href.lastIndexOf("/") + 1, href.lastIndexOf("."));
			String detailUrl = MessageFormat.format(CommonUtil.DETAIL_URL_REGEX, categoryId, categoryId);
			DeriveSchedulerContext.into(request.subRequest(detailUrl));
		});
	}
}
