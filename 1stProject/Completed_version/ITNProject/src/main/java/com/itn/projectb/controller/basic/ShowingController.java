package com.itn.projectb.controller.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itn.projectb.service.basic.ShowingService;
import com.itn.projectb.vo.basic.ShowingVO;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class ShowingController {
	
	@Autowired
	ShowingService showingService;
	
	@GetMapping("/showing/view/add")
	public String createShowingView() {
		return "showing/showing_add";
	}
	
	@ResponseBody
	@PostMapping("/showing/add")
	public String createShowing(@RequestBody ShowingVO showingVO) {
		showingService.createShowing(showingVO);
		return "1";
	}
}
