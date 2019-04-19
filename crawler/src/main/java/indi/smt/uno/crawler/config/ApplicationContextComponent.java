package indi.smt.uno.crawler.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author 無痕剑
 * @date 2019/4/18 21:01
 */
@Component
public class ApplicationContextComponent implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return this.applicationContext;
	}

	public <T> T getBean(String beanName, Class<T> tClass) {
		return applicationContext.getBean(beanName, tClass);
	}
}
