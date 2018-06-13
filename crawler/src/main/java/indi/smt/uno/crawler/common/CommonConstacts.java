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
	public final static String DOMAIN = "9191ss.com";

	/** 显示 */
	public final static Integer SHOW = 1;
	/** 隐藏 */
	public final static Integer HIDDEN = 2;

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

	/**
	 * 用最大值分割数组，最小返回1，最大返回max，数组长度介于两者直接则返回数组长度
	 * @param list 数组
	 * @param max 最大值
	 * @return 返回结果
	 */
	public static int segmentation(List list, int max) {
		int size = list.size();
		return size > max ? max : (size == 0 ? 1 : size);
	}

	/**
	 * 判断字符串是空串：null，""，" "（多个空格）
	 */
	public static boolean isBlank(String string) {
		return string == null || string.length() == 0 || string.matches("^[ ]+$");
	}
}
