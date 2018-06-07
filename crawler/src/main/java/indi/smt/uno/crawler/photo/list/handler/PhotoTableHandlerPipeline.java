package indi.smt.uno.crawler.photo.list.handler;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.spider.HrefBean;
import indi.smt.uno.crawler.common.CommonConstacts;
import indi.smt.uno.crawler.photo.list.model.PhotoTable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SwordNoTrace
 * @date 2018/6/7 0:03
 */
@PipelineName("photoTableHandlerPipeline")
public class PhotoTableHandlerPipeline implements Pipeline<PhotoTable> {

	public static List<String> photoDetailUrlList = new ArrayList<>();

	@Override
	public void process(PhotoTable bean) {
		for (HrefBean hb : bean.getPhotoDetailList()) {
			String url = hb.getUrl();
			if (CommonConstacts.isBlank(url)) {
				continue;
			}
			photoDetailUrlList.add(url);
		}
	}
}
