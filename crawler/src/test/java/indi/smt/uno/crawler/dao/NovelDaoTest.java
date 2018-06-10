package indi.smt.uno.crawler.dao;

import indi.smt.uno.crawler.common.SqlSessionUtil;
import indi.smt.uno.crawler.entity.Novel;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * @author SwordNoTrace
 * @date 2018/6/10 22:36
 */
public class NovelDaoTest {

	private NovelDao novelDao;

	@Before
	public void before() {
		novelDao = SqlSessionUtil.getDao(NovelDao.class);
	}

	@Test
	public void countTest() {
		System.out.println(novelDao.count());
	}

	@Test
	public void insertTest() {
		Novel novel = new Novel();
		novel.setAuthor("街坊四邻");
		novel.setCls("来来往往");
		novel.setContent("建档立卡赛季阿克拉");
		novel.setInsertTime(new Date());
		novel.setTitle("海富商店");
		novelDao.insert(novel);
		System.out.println(novel);
	}

	@Test
	public void selectAllTest() {
		System.out.println(novelDao.selectAll());
	}
}
