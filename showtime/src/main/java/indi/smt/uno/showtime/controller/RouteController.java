package indi.smt.uno.showtime.controller;

import indi.smt.uno.showtime.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author mayuhan
 * @date 2019/4/16 15:01
 */
@Controller
public class RouteController {

	private final VideoService videoService;

	@Autowired
	public RouteController(VideoService videoService) {
		this.videoService = videoService;
	}

	@GetMapping("/index")
	public String index(Model model) {
//		videoService.listByCategory()
		return "index";
	}

	@GetMapping("/category")
	public String category(Model model) {
		model.addAttribute("");
		return "category";
	}
}
