package indi.smt.uno.download.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author 無痕剑
 * @date 2019/4/16 0:13
 */
@Component
public class DownloadEventPublishService {

	private final ApplicationContext applicationContext;

	@Autowired
	public DownloadEventPublishService(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void publish(DownloadEvent event) {
		applicationContext.publishEvent(event);
	}
}
