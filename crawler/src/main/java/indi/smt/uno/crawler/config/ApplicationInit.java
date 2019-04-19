package indi.smt.uno.crawler.config;

import indi.smt.uno.crawler.dao.VideoRepository;
import indi.smt.uno.crawler.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 無痕剑
 * @date 2019/4/18 20:48
 */
@Component
public class ApplicationInit implements ApplicationRunner {

	private final VideoRepository videoRepository;

	private final ApplicationContextComponent applicationContextComponent;

	@Autowired
	public ApplicationInit(VideoRepository videoRepository,
	                       ApplicationContextComponent applicationContextComponent) {
		this.videoRepository = videoRepository;
		this.applicationContextComponent = applicationContextComponent;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("开始获取数据库数据");
		Set<String> existSet = videoRepository.findAll()
				.parallelStream()
				.map(Video::getTitle)
				.collect(Collectors.toSet());
		System.out.println("已经存在的视频共：" + existSet.size() + "条");
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContextComponent.getApplicationContext().getAutowireCapableBeanFactory();
		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(HashSet.class)
				.addConstructorArgValue(existSet)
				.getBeanDefinition();
		beanFactory.registerBeanDefinition("existSet", beanDefinition);
		System.out.println("创建已存在的视频列表bean");
	}
}
