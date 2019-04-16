package indi.smt.uno.save.msg;

import com.alibaba.fastjson.JSON;
import indi.smt.uno.save.common.CommonUtil;
import indi.smt.uno.save.dao.VideoRepository;
import indi.smt.uno.save.entity.Video;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author 無痕剑
 * @date 2019/4/14 20:19
 */
@Component
@RabbitListener(queues = CommonUtil.VIDEO_SAVE)
public class MsgListener {

	private final VideoRepository videoRepository;

	@Autowired
	public MsgListener(VideoRepository videoRepository) {
		this.videoRepository = videoRepository;
	}

	@RabbitHandler
	@Transactional(rollbackFor = Exception.class)
	public void videoDownloadUrlListener(String message) {
		System.out.println("接受到消息：" + message);
		try {
			Video video = JSON.parseObject(message, Video.class);
			video.setCreateTime(new Date());
			video.setIsShow(1);
			Video oldVideo = videoRepository.findByTitleAndCategory(video.getTitle(), video.getCategory());
			if (oldVideo != null) {
				return;
			}
			videoRepository.save(video);
		} catch (Exception e) {
			System.out.println("持久化消息发生异常--------->");
			System.out.println(CommonUtil.exceptionString(e));
			throw e;
		}
	}
}
