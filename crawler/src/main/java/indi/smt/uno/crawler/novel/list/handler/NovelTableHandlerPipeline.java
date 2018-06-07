package indi.smt.uno.crawler.novel.list.handler;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.spider.HrefBean;
import indi.smt.uno.crawler.common.CommonConstacts;
import indi.smt.uno.crawler.novel.list.model.NovelTable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SwordNoTrace
 * @date 2018/6/7 0:03
 */
@PipelineName("novelTableHandlerPipeline")
public class NovelTableHandlerPipeline implements Pipeline<NovelTable> {

	public static List<String> novelDetailUrlList = new ArrayList<>();

	@Override
	public void process(NovelTable bean) {
		for (HrefBean hb : bean.getNovelDetailList()) {
			String url = hb.getUrl();
			if (CommonConstacts.isBlank(url)) {
				continue;
			}
			novelDetailUrlList.add(url);
		}
	}
}
