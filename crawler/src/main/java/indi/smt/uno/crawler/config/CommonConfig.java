package indi.smt.uno.crawler.config;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.spring.SpringGeccoEngine;
import indi.smt.uno.crawler.common.CommonUtil;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author 無痕剑
 * @date 2019/4/10 0:31
 */
@Configuration
public class CommonConfig {

	@Value("${gecco.thread}")
	private int threadCount;

	@Value("${gecco.interval}")
	private int interval;

	@Bean
	public SpringGeccoEngine springGeccoEngine() {
		return new SpringGeccoEngine() {
			@Override
			public void init() {
				GeccoEngine.create()
						.pipelineFactory(springPipelineFactory)
						.classpath("indi.smt.uno.crawler.entity")
						.start(CommonUtil.BASE_URL)
						.thread(threadCount)
						.interval(interval)
						.start();
			}
		};
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient()));
	}

	private HttpClient httpClient()
	{
		// 支持HTTP、HTTPS
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory())
				.register("https", SSLConnectionSocketFactory.getSocketFactory())
				.build();
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
		connectionManager.setMaxTotal(2000);
		connectionManager.setDefaultMaxPerRoute(1000);
		connectionManager.setValidateAfterInactivity(2000);
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(65000) // 服务器返回数据(response)的时间，超时抛出read timeout
				.setConnectTimeout(5000) // 连接上服务器(握手成功)的时间，超时抛出connect timeout
				.setConnectionRequestTimeout(10000)// 从连接池中获取连接的超时时间，超时抛出ConnectionPoolTimeoutException
				.build();
		return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).setConnectionManager(connectionManager).build();
	}

	@Bean
	public Queue queue() {
		return new Queue(CommonUtil.VIDEO_DOWNLOAD_URL_QUEUE);
	}
}
