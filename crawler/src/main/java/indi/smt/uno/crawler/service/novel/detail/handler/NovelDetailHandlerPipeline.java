package indi.smt.uno.crawler.service.novel.detail.handler;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import indi.smt.uno.crawler.common.CommonConstacts;
import indi.smt.uno.crawler.dao.CategoryMapper;
import indi.smt.uno.crawler.dao.NovelMapper;
import indi.smt.uno.crawler.dao.SqlSessionUtil;
import indi.smt.uno.crawler.entity.Novel;
import indi.smt.uno.crawler.service.novel.detail.model.NovelDetail;

import java.util.Date;

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
        try (SqlSessionUtil sqlSessionUtil = new SqlSessionUtil()) {
            Integer cateId = sqlSessionUtil.getDao(CategoryMapper.class).selectByDomainAndCateName(CommonConstacts.DOMAIN, bean.getType());
            Novel novel = new Novel();
            novel.setCateId(cateId);
            novel.setTitle(bean.getTitle());
            Date time = new Date();
            novel.setInsertTime(time);
            novel.setCreateTime(time);
            novel.setContent(bean.getContext());
//            novel.setAuthor();
            novel.setIsShow(CommonConstacts.SHOW);
            sqlSessionUtil.getDao(NovelMapper.class).insertSelective(novel);
        } catch (Exception e) {
            System.out.println("发生异常：" + e.getMessage());
        }
    }
}
