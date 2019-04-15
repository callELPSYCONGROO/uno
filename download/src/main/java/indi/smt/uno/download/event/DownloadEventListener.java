package indi.smt.uno.download.event;

import org.apache.commons.collections4.ListUtils;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

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
		List<List<String>> partition = ListUtils.partition(segmentationList, 10);

	}
}
