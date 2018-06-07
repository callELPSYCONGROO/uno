package indi.smt.uno.crawler.common;

import org.junit.Test;

/**
 * @author SwordNoTrace
 * @date 2018/6/6 23:59
 */
public class CommonConstactsTest {

    /**
     * 判断字符串是空串：null，""，" "（多个空格）
     */
    @Test
	public void isBlankTest() {
		System.out.println(CommonConstacts.isBlank("  . "));
	}
}
