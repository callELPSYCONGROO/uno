package indi.smt.uno.crawler.dao;

import indi.smt.uno.crawler.entity.Photo;
import indi.smt.uno.crawler.entity.PhotoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhotoMapper {
    int countByCriteria(PhotoCriteria criteria);

    int deleteByCriteria(PhotoCriteria criteria);

    int deleteByPrimaryKey(Integer id);

    int insert(Photo record);

    int insertSelective(Photo record);

    List<Photo> selectByCriteria(PhotoCriteria criteria);

    Photo selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") Photo record, @Param("criteria") PhotoCriteria criteria);

    int updateByCriteria(@Param("record") Photo record, @Param("criteria") PhotoCriteria criteria);

    int updateByPrimaryKeySelective(Photo record);

    int updateByPrimaryKey(Photo record);

    int countByModel(Photo model);

    List<Photo> selectByModel(Photo model);

    void insertBatch(List<Photo> list);
}