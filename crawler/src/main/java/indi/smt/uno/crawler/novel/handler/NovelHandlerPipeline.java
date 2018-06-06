package indi.smt.uno.crawler.novel.handler;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import indi.smt.uno.crawler.novel.model.NovelTable;

/**
 * @author SwordNoTrace
 * @date 2018/6/7 0:03
 */
@PipelineName("novelHandlerPipeline")
public class NovelHandlerPipeline implements Pipeline<NovelTable> {

	@Override
	public void process(NovelTable bean) {

	}
}
