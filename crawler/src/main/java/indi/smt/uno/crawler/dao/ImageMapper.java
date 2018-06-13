package indi.smt.uno.crawler.dao;

import indi.smt.uno.crawler.entity.Image;
import indi.smt.uno.crawler.entity.ImageCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ImageMapper {
    int countByCriteria(ImageCriteria criteria);

    int deleteByCriteria(ImageCriteria criteria);

    int deleteByPrimaryKey(Integer id);

    int insert(Image record);

    int insertSelective(Image record);

    List<Image> selectByCriteria(ImageCriteria criteria);

    Image selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") Image record, @Param("criteria") ImageCriteria criteria);

    int updateByCriteria(@Param("record") Image record, @Param("criteria") ImageCriteria criteria);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);

    int countByModel(Image model);

    List<Image> selectByModel(Image model);

    void insertBatch(List<Image> list);
}