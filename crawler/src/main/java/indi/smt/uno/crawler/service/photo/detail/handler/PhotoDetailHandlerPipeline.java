package indi.smt.uno.crawler.service.photo.detail.handler;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import indi.smt.uno.crawler.common.CommonConstacts;
import indi.smt.uno.crawler.dao.CategoryMapper;
import indi.smt.uno.crawler.dao.ImageMapper;
import indi.smt.uno.crawler.dao.PhotoMapper;
import indi.smt.uno.crawler.dao.SqlSessionUtil;
import indi.smt.uno.crawler.entity.Image;
import indi.smt.uno.crawler.entity.Photo;
import indi.smt.uno.crawler.service.photo.detail.model.PhotoDetail;
import indi.smt.uno.crawler.service.photo.detail.model.PhotoImage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		try (SqlSessionUtil sqlSessionUtil = new SqlSessionUtil()) {
			// 查找对应的分类id
			Integer cateId = sqlSessionUtil.getDao(CategoryMapper.class).selectByDomainAndCateName(CommonConstacts.DOMAIN, bean.getType());
			Photo photo = new Photo();
			photo.setCateId(cateId);
			photo.setTitle(bean.getTitle());
			Date time = new Date();
			photo.setCreateTime(time);
			photo.setInsertTime(time);
			photo.setIsShow(CommonConstacts.SHOW);
			// 插入图片页面信息
			sqlSessionUtil.getDao(PhotoMapper.class).insertSelective(photo);
			List<Image> imageList = new ArrayList<>();
			for (int i = 0; i < bean.getImageSrcList().size(); i++) {
				Image image = new Image();
				image.setPhotoId(photo.getId());
				image.setUri(bean.getImageSrcList().get(i).getImg());
				image.setSort(i + 1);
				imageList.add(image);
			}
			// 插入图片uri
			sqlSessionUtil.getDao(ImageMapper.class).insertBatch(imageList);
		} catch (Exception e) {
			System.out.println("发生异常：" + e.getMessage());
		}
	}
}
