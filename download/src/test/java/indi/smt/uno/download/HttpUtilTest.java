package indi.smt.uno.download;

import indi.smt.uno.download.common.HttpUtil;
import org.junit.Test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * @author 無痕剑
 * @date 2019/4/15 23:38
 */
public class HttpUtilTest {

	@Test
	public void getTest() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		String s = HttpUtil.httpsGetForString("https://dadi-bo.com/20181214/urUFcsaB/index.m3u8");
		System.out.println(s);
		String[] split = s.split("\n");
		for (String p : split) {
			System.out.println("--->" + p);
		}
	}
}
