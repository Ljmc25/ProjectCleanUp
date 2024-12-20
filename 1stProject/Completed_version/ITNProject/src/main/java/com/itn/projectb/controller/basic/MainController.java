package com.itn.projectb.controller.basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.itn.projectb.service.basic.ProductService;

import lombok.extern.log4j.Log4j;

/**
 * @author
 *
 */
@Log4j
@Controller
public class MainController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/main")
	public String mainView(Model model) {
		List<?> productList = productService.selectLimitList();
		model.addAttribute("productList",productList);
		
		return "main/main";
	}
}
