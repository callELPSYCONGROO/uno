package indi.smt.uno.crawler;

import indi.smt.uno.crawler.common.HttpUtil;
import org.junit.Test;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * @author 無痕剑
 * @date 2019/4/14 17:56
 */
public class HttpUtilTest {

	@Test
	public void httpsGetTest() throws ClassNotFoundException, IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, ScriptException {
//		InputStream inputStream = HttpUtil.httpsGetForInputstream("https://usa-10.us/upload/playdata/20190412/53541/53541.js");
//		OutputStream os = new FileOutputStream("C:\\Users\\SwordNoTrace\\Desktop\\1.js");
//		int r;
//		byte[] bytes = new byte[1024];
//		StringBuilder sb = new StringBuilder();
//		while ((r = inputStream.read(bytes, 0, 1024)) != -1) {
//			sb.append(new String(bytes));
//			os.write(bytes, 0, r);
//		}
//
//		String s = sb.toString();
		String s = HttpUtil.httpsGetForString("https://usa-10.us/upload/playdata/20190412/53541/53541.js");

		System.out.println(s);
		String[] splits = s.split(",");
		String uri = null;
		for (int i = splits.length - 1; i >= 0; i--) {
			String ss = splits[i];
			String[] kv = ss.split("=");
			if (kv[1].startsWith("unescape")) {
				String replace = kv[1].replace("');", "");
				String encodeUri = replace.substring(replace.indexOf("https"));
				uri = URLDecoder.decode(encodeUri, "UTF-8");
			}
		}

		System.out.println(uri);

//		os.flush();
//		inputStream.close();
//		os.close();
	}
}
