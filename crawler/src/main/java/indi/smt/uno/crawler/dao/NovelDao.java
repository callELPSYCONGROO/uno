package indi.smt.uno.crawler.dao;

import indi.smt.uno.crawler.entity.Novel;

import java.util.List;

/**
 * @author SwordNoTrace
 * @date 2018/6/10 22:03
 */
public interface NovelDao {

	Integer count();

	Integer insert(Novel novel);

	List<Novel> selectAll();
}
