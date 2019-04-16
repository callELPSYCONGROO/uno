package indi.smt.uno.download;

import indi.smt.uno.download.common.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * @author 無痕剑
 * @date 2019/4/15 23:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpUtilTest {

	@Value("${download.base-path}")
	private String basePath;


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
		// 创建目录
		File dir = new File(fileName.substring(0, fileName.lastIndexOf(File.separator)));
		if (dir.mkdirs()) {
			System.out.println("创建下载目录\"" + dir.getPath() + "\"成功--------------->");
		}
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
	public void s() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		String prefix = basePath + File.separator + "video" + File.separator + "category" + File.separator + "title" + File.separator;
		String segmentation = "https://iqiyi.qq-zuidazy.com/20190411/8859_9d3e17fa/800k/hls/153eda30ab5000010.ts";
		InputStream inputStream = HttpUtil.httpsGetForInputstream(segmentation);
		String fileName = prefix + segmentation.substring(segmentation.lastIndexOf("/") + 1);
		write(inputStream, fileName);
	}
}
