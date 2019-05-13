package indi.smt.uno.categorycrawler.event;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.listener.SimpleEventListener;
import indi.smt.uno.categorycrawler.common.CommonUtil;
import org.springframework.stereotype.Component;

/**
 * @author mayuhan
 * @date 2019/4/16 12:49
 */
@Component
public class GeccoStatusListener extends SimpleEventListener {

	@Override
	public void onStart(GeccoEngine ge) {
		System.out.println("****************开始爬取【" + CommonUtil.BASE_URL + "】内容");
	}

	@Override
	public void onStop(GeccoEngine ge) {
		System.out.println("****************爬取【" + CommonUtil.BASE_URL + "】内容完成");
		System.exit(0);
	}
}
