package indi.smt.uno.crawler.event;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.listener.SimpleEventListener;
import indi.smt.uno.crawler.common.CommonUtil;
import indi.smt.uno.crawler.config.MailHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mayuhan
 * @date 2019/4/16 12:49
 */
@Component
public class GeccoStatusListener extends SimpleEventListener {

	private final MailHelper mailHelper;

	@Autowired
	public GeccoStatusListener(MailHelper mailHelper) {
		this.mailHelper = mailHelper;
	}

	@Override
	public void onStart(GeccoEngine ge) {
		System.out.println("****************开始爬取【" + CommonUtil.BASE_URL + "】内容");
	}

	@Override
	public void onStop(GeccoEngine ge) {
		System.out.println("****************爬取【" + CommonUtil.BASE_URL + "】内容完成");
		mailHelper.send("【UNO】爬取网页信息完成", "爬取【" + CommonUtil.BASE_URL + "】内容完成");
	}
}
