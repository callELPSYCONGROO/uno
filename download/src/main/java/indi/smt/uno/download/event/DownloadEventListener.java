package indi.smt.uno.download.event;

import indi.smt.uno.download.common.CommonUtil;
import indi.smt.uno.download.common.HttpUtil;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 無痕剑
 * @date 2019/4/16 0:08
 */
@Component
public class DownloadEventListener {

	@Value("${download.base-path}")
	private String basePath;

	@EventListener(DownloadEvent.class)
	@Async
	public void handlerEvent(DownloadEvent event) {
		List<String> segmentationList = event.getSegmentationList();
		// 创建线程池
		ExecutorService executorService = new ThreadPoolExecutor(
				0,
				segmentationList.size() / 10 + 1,// 每个线程下载10个段落
				1,
				TimeUnit.HOURS,
				new SynchronousQueue<>()
		);

		// 将集合每10个分成一段
		List<List<String>> partitionList = ListUtils.partition(segmentationList, 10);
		// 文件名前缀
		String prefix = basePath + File.separator + "video" + File.separator + event.getCategory() + File.separator + event.getTitle() + File.separator;

		for (List<String> partition : partitionList) {
			Runnable runnable = () -> {
				for (String segmentation : partition) {
					String fileName = null;
					try {
						InputStream inputStream = HttpUtil.httpsGetForInputstream(segmentation);
						fileName = prefix + segmentation.substring(segmentation.lastIndexOf("/") + 1);
						write(inputStream, fileName);
					} catch (Exception e) {
						System.out.println("<" + fileName + "><" + segmentation +">------------>下载文件，写文件发生异常：");
						System.out.println(CommonUtil.exceptionString(e));
					}
				}
			};
			executorService.execute(runnable);
		}
	}

	private void write(InputStream is, String fileName) throws IOException {
		BufferedInputStream in = new BufferedInputStream(is);
		// 创建目录
		File dir = new File(fileName.substring(0, fileName.lastIndexOf(File.separator)));
		if (dir.mkdirs()) {
			System.out.println("创建下载目录\"" + dir.getPath() + "\"成功--------------->");
		}
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(fileName)));
		int len;
		byte[] b = new byte[1024];
		while ((len = in.read(b)) != -1) {
			out.write(b, 0, len);
		}
		in.close();
		out.close();
	}
}
