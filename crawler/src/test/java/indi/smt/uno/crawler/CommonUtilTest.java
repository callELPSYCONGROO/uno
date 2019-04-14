package indi.smt.uno.crawler;

import indi.smt.uno.crawler.common.CommonUtil;
import org.junit.Test;

/**
 * @author 無痕剑
 * @date 2019/4/10 23:37
 */
public class CommonUtilTest {

	@Test
	public void matchTest() {
//		//共\d+条数据( )*当前:\d+/\d+页( )*
//		Matcher matcher = Pattern.compile("\\d+").matcher("共589条数据 当前:15/20页 ");
//		while (matcher.find()) {
//			System.out.println(matcher.group());
//		}
		String nextUri = CommonUtil.nextUri("pagego('/vodtypehtml/24-{pg}.html',20)", 1001);
		String nextUrl = CommonUtil.BASE_URL + nextUri;
		System.out.println(nextUrl);
	}
}
