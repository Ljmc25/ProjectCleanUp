package com.itn.projectb.controller.advanced;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@GetMapping("/admin/main")
	public String adminPage() {
		return "admin/main/main";
	}
}
