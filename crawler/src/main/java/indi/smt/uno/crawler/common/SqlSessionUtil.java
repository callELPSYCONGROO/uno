package indi.smt.uno.crawler.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * @author SwordNoTrace
 * @date 2018/6/10 22:28
 */
public class SqlSessionUtil {

	private static SqlSessionFactory sqlSessionFactory;

	static {
		try {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SqlSession get() {
		return sqlSessionFactory.openSession(true);
	}

	public static <T> T getDao(Class<T> tClass) {
		return get().getMapper(tClass);
	}

	public static void close(SqlSession sqlSession) {
		sqlSession.close();
	}
}
