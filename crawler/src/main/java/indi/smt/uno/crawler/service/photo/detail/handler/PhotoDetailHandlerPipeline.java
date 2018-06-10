package indi.smt.uno.crawler.service.photo.detail.handler;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import indi.smt.uno.crawler.service.photo.detail.model.PhotoDetail;

/**
 * PhotoDetailHandlerPipeline
 * @author: Overload
 * @date: 2018/6/7 20:56
 * @version: 1.0
 */
@PipelineName("photoDetailHandlerPipeline")
public class PhotoDetailHandlerPipeline implements Pipeline<PhotoDetail> {

    @Override
    public void process(PhotoDetail bean) {
        System.out.println(bean);
    }
}
