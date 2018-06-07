package indi.smt.uno.crawler.novel.detail.handler;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import indi.smt.uno.crawler.novel.detail.model.NovelDetail;

/**
 * NovelDetailHandlerPipeline
 * @author: Overload
 * @date: 2018/6/7 20:56
 * @version: 1.0
 */
@PipelineName("novelDetailHandlerPipeline")
public class NovelDetailHandlerPipeline implements Pipeline<NovelDetail> {

    @Override
    public void process(NovelDetail bean) {
        System.out.println(bean.getTitle());
    }
}
