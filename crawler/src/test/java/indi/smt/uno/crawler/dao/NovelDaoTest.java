package indi.smt.uno.crawler.dao;

import indi.smt.uno.crawler.entity.Novel;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * @author SwordNoTrace
 * @date 2018/6/10 22:36
 */
public class NovelDaoTest {

	@Test
	public void countTest() {
		try (SqlSessionUtil sqlSessionUtil = new SqlSessionUtil()) {
			System.out.println(sqlSessionUtil.getDao(NovelDao.class).count());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void insertTest() {
		try (SqlSessionUtil sqlSessionUtil = new SqlSessionUtil()) {
			Novel novel = new Novel();
			novel.setAuthor("123");
			novel.setCls("54");
			novel.setContent("mfklsdjfoisfd");
			novel.setInsertTime(new Date());
			novel.setTitle("海富商店");
			sqlSessionUtil.getDao(NovelDao.class).insert(novel);
			System.out.println(novel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void selectAllTest() {
		try (SqlSessionUtil sqlSessionUtil = new SqlSessionUtil()) {
			System.out.println(sqlSessionUtil.getDao(NovelDao.class).selectAll());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
