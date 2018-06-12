package indi.smt.uno.crawler.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author SwordNoTrace
 * @date 2018/6/10 22:28
 */
public class SqlSessionUtil implements Closeable {

	private static SqlSessionFactory sqlSessionFactory;

	private SqlSession sqlSession;

	static {
		try {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public SqlSessionUtil() {
		sqlSession = getNewSqlSession();
	}

	public static SqlSession getNewSqlSession() {
		return sqlSessionFactory.openSession(true);
	}

	public <T> T getDao(Class<T> tClass) {
		return sqlSession.getMapper(tClass);
	}

	@Override
	public void close() {
		sqlSession.close();
	}
}
