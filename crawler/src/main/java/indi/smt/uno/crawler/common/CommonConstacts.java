package indi.smt.uno.crawler.common;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author SwordNoTrace
 * @date 2018/6/6 23:59
 */
public class CommonConstacts {

	/**
	 * 爬取网页首页
	 */
	public final static String BASE_URL = "http://www.9191ss.com/";

	/**
	 * 组装分页路径
	 * @param pageNum 页码
	 * @return 分页路径
	 */
	public static String pageIndexPath(int pageNum) {
		return "index-" + pageNum + ".html";
	}

	/**
	 * 判断是否为该集合所含的类型
	 * @param type 类型
	 * @param typeList 类型集合
	 */
	public static boolean isThisType(String type, List<String> typeList) {
		AtomicBoolean result = new AtomicBoolean(false);
		typeList.forEach(t -> {
			if (t.contains("/" + type + "/")) {
				result.set(true);
			}
		});
		return result.get();
	}
}
