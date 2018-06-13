package indi.smt.uno.crawler.dao;

import indi.smt.uno.crawler.entity.Novel;
import indi.smt.uno.crawler.entity.NovelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NovelMapper {
    int countByCriteria(NovelCriteria criteria);

    int deleteByCriteria(NovelCriteria criteria);

    int deleteByPrimaryKey(Integer id);

    int insert(Novel record);

    int insertSelective(Novel record);

    List<Novel> selectByCriteriaWithBLOBs(NovelCriteria criteria);

    List<Novel> selectByCriteria(NovelCriteria criteria);

    Novel selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") Novel record, @Param("criteria") NovelCriteria criteria);

    int updateByCriteriaWithBLOBs(@Param("record") Novel record, @Param("criteria") NovelCriteria criteria);

    int updateByCriteria(@Param("record") Novel record, @Param("criteria") NovelCriteria criteria);

    int updateByPrimaryKeySelective(Novel record);

    int updateByPrimaryKeyWithBLOBs(Novel record);

    int updateByPrimaryKey(Novel record);

    int countByModel(Novel model);

    List<Novel> selectByModelWithBLOBs(Novel model);

    List<Novel> selectByModel(Novel model);

    void insertBatch(List<Novel> list);
}