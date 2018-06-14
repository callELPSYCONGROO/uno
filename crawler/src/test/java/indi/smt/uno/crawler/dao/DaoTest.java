package indi.smt.uno.crawler.dao;

import org.junit.Before;
import org.junit.Test;

/**
 * DaoTest
 * @author: Overload
 * @date: 2018/6/14 13:33
 * @version: 1.0
 */
public class DaoTest {

    private SqlSessionUtil sqlSessionUtil;

    @Before
    public void before() {
        sqlSessionUtil = new SqlSessionUtil();
    }

    @Test
    public void count() {
        int photoCount = sqlSessionUtil.getDao(PhotoMapper.class).countByModel(null);
        int novelCount = sqlSessionUtil.getDao(NovelMapper.class).countByModel(null);
        int imageCount = sqlSessionUtil.getDao(ImageMapper.class).countByModel(null);
        System.out.printf("photo = %s\nnovel = %s\nimage = %s\n", photoCount, novelCount, imageCount);
    }
}
