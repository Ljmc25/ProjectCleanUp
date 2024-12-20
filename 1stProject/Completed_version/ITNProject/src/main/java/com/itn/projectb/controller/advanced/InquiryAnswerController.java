package com.itn.projectb.controller.advanced;

import java.util.List;
import javax.annotation.Resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springmodules.validation.commons.DefaultBeanValidator;

import com.itn.projectb.service.advanced.InquiryAnswerService;

import com.itn.projectb.vo.advanced.InquiryAnswerVO;
import com.itn.projectb.vo.common.Criteria;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class InquiryAnswerController {

	@Autowired
	InquiryAnswerService inquiryAnswerService;
	
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@GetMapping("/inquiryAnswer")
	public String selectCt4List(@RequestParam String inid ,Model model,@ModelAttribute("searchVO") Criteria searchVO) throws Exception {
		
		List<?> inquiryAnswerList = inquiryAnswerService.selectCt4List(searchVO);
		model.addAttribute("inquiryAnswerList", inquiryAnswerList);
		model.addAttribute("inid",inid);
		return "center/inquiry/inquiryAnswer";
	}

	//  저장 버튼 클릭시 실행 함수 : insert 실행 -> post 방식
	@PostMapping("/inquiryAnswer/write")
	public String createCt4(@ModelAttribute("Ct4VO") InquiryAnswerVO inquiryAnswerVO) throws Exception {
		inquiryAnswerService.insertCt4(inquiryAnswerVO);  
		return "redirect:/inquiryList";  // 2) 전제조회 페이지로 강제이동  
	}

	//상세조회 페이지 열기 :
	@GetMapping("/asas/view")
	public String updateCt4View(@RequestParam String anid, Model model) throws Exception {
		//   1) deptService 의 상세조회 함수 실행 
		InquiryAnswerVO inquiryAnswerVO = inquiryAnswerService.selectCt4(anid);
		//   2) 모델에 담아 Jsp 로 전송
		model.addAttribute("inquiryAnswerVO", inquiryAnswerVO);

		return "oneononeas/asas";
	}	
}
