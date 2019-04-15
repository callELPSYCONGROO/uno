package indi.smt.uno.download.event;

import indi.smt.uno.download.common.CommonUtil;
import indi.smt.uno.download.common.HttpUtil;
import org.apache.commons.collections4.ListUtils;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author 無痕剑
 * @date 2019/4/16 0:08
 */
@Component
public class DownloadEventListener {

	@EventListener(DownloadEvent.class)
	@Async
	public void handlerEvent(DownloadEvent event) {
		List<String> segmentationList = event.getSegmentationList();
		// 创建线程池
		ExecutorService executorService = new ThreadPoolExecutor(
				0,
				segmentationList.size() / 10 + 1,// 每个线程下载10个段落
				1L,
				TimeUnit.MINUTES,
				new SynchronousQueue<>()
		);

		// 将集合每10个分成一段
		List<List<String>> partitionList = ListUtils.partition(segmentationList, 10);
		// 线程集合
		List<Callable<Boolean>> callableList = new ArrayList<>();
		// 文件名前缀
		String prefix = System.getProperty("user.dir") + File.separator + "video" + File.separator + event.getCategory() + File.separator + event.getTitle() + File.separator;

		for (List<String> partition : partitionList) {
			Callable<Boolean> runnable = () -> {
				for (String segmentation : partition) {
					try {
						InputStream inputStream = HttpUtil.httpsGetForInputstream(segmentation);
						write(inputStream, prefix + segmentation.substring(segmentation.lastIndexOf("/") + 1));
					} catch (Exception e) {
						System.out.println("**************下载文件，写文件发生异常：");
						System.out.println(CommonUtil.exceptionString(e));
					}
				}
				return true;
			};
			callableList.add(runnable);
		}

		// 执行线程
		try {
			executorService.invokeAny(callableList, 10, TimeUnit.MINUTES);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			System.out.println("线程执行发生错误----------->");
			System.out.println(CommonUtil.exceptionString(e));
		}
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
}
