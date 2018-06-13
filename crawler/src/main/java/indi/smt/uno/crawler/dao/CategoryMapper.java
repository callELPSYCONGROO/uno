package indi.smt.uno.crawler.dao;

import indi.smt.uno.crawler.entity.Category;
import indi.smt.uno.crawler.entity.CategoryCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CategoryMapper {
    int countByCriteria(CategoryCriteria criteria);

    int deleteByCriteria(CategoryCriteria criteria);

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByCriteria(CategoryCriteria criteria);

    Category selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") Category record, @Param("criteria") CategoryCriteria criteria);

    int updateByCriteria(@Param("record") Category record, @Param("criteria") CategoryCriteria criteria);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    int countByModel(Category model);

    List<Category> selectByModel(Category model);

    void insertBatch(List<Category> list);

    Integer selectByDomainAndCateName(@Param("d") String d, @Param("c") String c);
}