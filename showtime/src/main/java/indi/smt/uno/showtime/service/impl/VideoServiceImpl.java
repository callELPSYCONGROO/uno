package indi.smt.uno.showtime.service.impl;

import indi.smt.uno.showtime.dao.VideoRepository;
import indi.smt.uno.showtime.entity.Video;
import indi.smt.uno.showtime.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mayuhan
 * @date 2019/4/16 17:08
 */
@Service
public class VideoServiceImpl implements VideoService {

	private final VideoRepository videoRepository;

	@Autowired
	public VideoServiceImpl(VideoRepository videoRepository) {
		this.videoRepository = videoRepository;
	}

	@Override
	public List<String> listAllCategory() {
		return videoRepository.findAll()
				.stream()
				.map(Video::getCategory)
				.collect(Collectors.toList());
	}
}
