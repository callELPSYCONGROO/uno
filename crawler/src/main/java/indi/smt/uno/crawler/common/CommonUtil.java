package indi.smt.uno.crawler.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SwordNoTrace
 * @date 2018/6/6 23:59
 */
public class CommonUtil {

	public final static String BASE_URL = "https://usa-10.us";

	public final static String DOMAIN = "usa-10.us";

	public final static String NAVI_CATEGORY_REGEX = "/vodtypehtml/\\d+\\.html";

	public final static String NAVI_CATEGORY_MATCH = "/vodtypehtml/{pageId}";

	public final static String PAGINATION_REGEX = "共\\d+条数据( )*当前:\\d+/\\d+页( )*";

	public final static String PAGEBTN_REGEX = "pagego\\('/vodtypehtml/\\d+-\\{pg}\\.html',20\\)";

	public final static String PAGEBTN = "/vodtypehtml/{categoryId}-{pg}.html";

	/**
	 * 返回分页信息参数
	 * @param string 分页信息
	 * @return 第一个为总条数，第二个为当前页，第三个为总页数
	 */
	public static String[] matchPagination(String string) {
		Matcher matcher = Pattern.compile(PAGINATION_REGEX).matcher(string);
		if (!matcher.find()) {
			return null;
		}
		String[] paginations = new String[3];
		Matcher matcher1 = matcher.reset().usePattern(Pattern.compile("\\d+"));
		for (int i = 0;i <= 3;i++) {
			if (matcher1.find()) {
				paginations[i] = matcher1.group();
			}
		}
		return paginations;
	}

	/**
	 * 获取下一页URI
	 * @param pagebtn 分页按钮onclick属性值
	 * @param nextPage 下一页
	 * @return 下一页uri
	 */
	public static String nextUri(String pagebtn, int nextPage) {
		Matcher matcher = Pattern.compile(PAGEBTN_REGEX).matcher(pagebtn);
		if (!matcher.find()) {
			return null;
		}
		Matcher matcher1 = matcher.reset().usePattern(Pattern.compile("\\d+"));
		if (!matcher1.find()) {
			return null;
		}
		String categoryId = matcher1.group();
		return PAGEBTN.replace("{categoryId}", categoryId).replace("{pg}", String.valueOf(nextPage));
	}
}
