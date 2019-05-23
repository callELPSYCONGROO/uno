package indi.smt.uno.categorycrawler.pipeline;

import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext;
import indi.smt.uno.categorycrawler.common.CommonUtil;
import indi.smt.uno.categorycrawler.entity.RootPage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author 無痕剑
 * @date 2019/5/14 0:45
 */
@Service("rootPagePipeline")
public class RootPagePipeline implements Pipeline<RootPage> {

	private final static String HTML = ".html";

	@Value("${gecco.category-id}")
	private String categoryIds;

	@Value("${gecco.max-page}")
	private Integer maxPage;
	
	@Override
	public void process(RootPage bean) {
		HttpRequest request = bean.getRequest();
		for (String categoryId : categoryIds.split(",")) {
			String baseUrl = CommonUtil.VOD_TYPE_HTML + categoryId;
			DeriveSchedulerContext.into(request.subRequest(baseUrl + HTML));
			for (int i = 2; i <= maxPage; i++) {
				DeriveSchedulerContext.into(request.subRequest(baseUrl + "-" + i + HTML));
			}
		}
	}
}
