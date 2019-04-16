package indi.smt.uno.crawler.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author mayuhan
 * @date 2019/4/16 13:38
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.mail")
public class MailHelper {

	private String password;

	private String username;

	private String host;

	private String recipient;

	private final JavaMailSender javaMailSender;

	@Autowired
	public MailHelper(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void send(String subject, String content) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		//邮件发送人
		simpleMailMessage.setFrom(username);
		//邮件接收人
		simpleMailMessage.setTo(recipient);
		//邮件主题
		simpleMailMessage.setSubject(subject);
		//邮件内容
		simpleMailMessage.setText(content);
		javaMailSender.send(simpleMailMessage);
	}
}
