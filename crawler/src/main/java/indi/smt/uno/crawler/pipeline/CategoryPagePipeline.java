package indi.smt.uno.crawler.pipeline;

import com.geccocrawler.gecco.pipeline.Pipeline;
import indi.smt.uno.crawler.entity.CategoryPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 分类页面处理管道
 * @author 無痕剑
 * @date 2019/4/9 23:53
 */
@Slf4j
@Service("categoryPagePipeline")
public class CategoryPagePipeline implements Pipeline<CategoryPage> {

	@Override
	public void process(CategoryPage bean) {
		//TODO 获取信息创建两个子查询
		// 下一页子查询

		// 视频详情页查询
	}
}
