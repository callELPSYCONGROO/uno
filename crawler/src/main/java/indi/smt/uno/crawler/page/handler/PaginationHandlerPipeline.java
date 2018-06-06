package indi.smt.uno.crawler.page.handler;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import indi.smt.uno.crawler.common.CommonConstacts;
import indi.smt.uno.crawler.nav.handler.WebPageHandlerPipeline;
import indi.smt.uno.crawler.page.model.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取页面URL
 * @author SwordNoTrace
 * @date 2018/6/7 0:31
 */
@PipelineName("paginationHandlerPipeline")
public class PaginationHandlerPipeline implements Pipeline<Pagination> {

	public static List<String> novelPaginationUrlList = new ArrayList<>();

	public static List<String> photoPaginationUrlList = new ArrayList<>();

	@Override
	public void process(Pagination bean) {
		String typeBaseUrl = bean.getHttpRequest().getUrl();
		for (int i = bean.getTotalPageNum(); i > 0; i--) {
			if (CommonConstacts.isThisType(bean.getType(), WebPageHandlerPipeline.novelsCategories)) {
				addPageNumUrl2List(i, typeBaseUrl, novelPaginationUrlList);
			}
			if (CommonConstacts.isThisType(bean.getType(), WebPageHandlerPipeline.photoCategories)) {
				addPageNumUrl2List(i, typeBaseUrl, photoPaginationUrlList);

			}
		}
	}

	private void addPageNumUrl2List(int i, String baseUrl, List<String> urlList) {
		String pageIndexPath = CommonConstacts.pageIndexPath(i);
		String paginationUrl = baseUrl + pageIndexPath;
		urlList.add(paginationUrl);
	}
}
