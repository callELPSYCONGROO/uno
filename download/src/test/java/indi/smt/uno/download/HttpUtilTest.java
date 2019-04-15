package indi.smt.uno.download;

import indi.smt.uno.download.common.HttpUtil;
import org.junit.Test;

import java.io.*;
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

	@Test
	public void postTest() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		File file = new File("xH1yYisa1256013.ts");
		if (!file.exists()) {
			boolean newFile = file.createNewFile();
			System.out.println(newFile);
		}
		InputStream inputStream = HttpUtil.httpsGetForInputstream("https://dadi-bo.com/20181214/FDl4z61W/500kb/hls/xH1yYisa1256013.ts");
		write(inputStream, "xH1yYisa1256013.ts");
	}

	private void write(InputStream is, String fileName) throws IOException {
		BufferedInputStream in = new BufferedInputStream(is);
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileName));
		int len;
		byte[] b = new byte[1024];
		while ((len = in.read(b)) != -1) {
			out.write(b, 0, len);
		}
		in.close();
		out.close();
	}

	@Test
	public void s() {
		System.out.println(System.getProperty("user.dir"));
	}
}
