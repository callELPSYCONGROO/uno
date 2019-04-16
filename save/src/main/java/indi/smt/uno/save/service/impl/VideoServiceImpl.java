package indi.smt.uno.save.service.impl;

import com.alibaba.fastjson.JSON;
import indi.smt.uno.save.common.CommonUtil;
import indi.smt.uno.save.dao.VideoRepository;
import indi.smt.uno.save.entity.Video;
import indi.smt.uno.save.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author mayuhan
 * @date 2019/4/16 11:19
 */
@Service
public class VideoServiceImpl implements VideoService {

	private final VideoRepository videoRepository;

	@Autowired
	public VideoServiceImpl(VideoRepository videoRepository) {
		this.videoRepository = videoRepository;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(String message) {
		try {
			Video video = JSON.parseObject(message, Video.class);
			if (StringUtils.isEmpty(video.getTitle())) {
				System.out.println("video-title can not be empty...");
				return;
			}
			if (StringUtils.isEmpty(video.getCategory())) {
				System.out.println("video-category can not be empty...");
				return;
			}
			if (StringUtils.isEmpty(video.getDownload())) {
				System.out.println("video-download can not be empty...");
				return;
			}
			video.setCreateTime(new Date());
			video.setIsShow(2);
			Video oldVideo = videoRepository.findByTitleAndCategory(video.getTitle(), video.getCategory());
			if (oldVideo != null) {
				return;
			}
			videoRepository.save(video);
		} catch (Exception e) {
			System.out.println("持久化消息发生异常--------->");
			System.out.println(CommonUtil.exceptionString(e));
		}
	}
}
