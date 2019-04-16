package indi.smt.uno.download.common;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author SwordNoTrace
 * @date 2018/6/6 23:59
 */
public class CommonUtil {

	public final static String VIDEO_DOWNLOAD = "video.download";

	public final static String DOWNLOAD_BASE_URL = "https://dadi-bo.com";

	public static String exceptionString(Throwable e) {
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter, true));
		return stringWriter.toString();
	}

	public static String changeUrlPath(String url, String path) {
		return url.substring(0, url.lastIndexOf("/") + 1) + path;
	}
}
