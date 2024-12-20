package com.itn.projectb.controller.advanced;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itn.projectb.service.advanced.InquiryAnswerService;
import com.itn.projectb.service.advanced.InquiryService;
import com.itn.projectb.vo.advanced.InquiryAnswerVO;
import com.itn.projectb.vo.advanced.InquiryVO;
import com.itn.projectb.vo.basic.MemberVO;
import com.itn.projectb.vo.common.Criteria;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller

public class InquiryController {
	
	@Autowired
	private InquiryService inquiryService;
	
	@Autowired
	private InquiryAnswerService inquiryAnswerService;
	
	@GetMapping("/inquiryList")
	public String selectCtList(@RequestParam(required = false) String inid,
	                            @ModelAttribute("searchVO") Criteria searchVO,
	                            Model model) throws Exception {

		// 데이터 조회를 위한 페이징 설정
	    searchVO.setPageUnit(5);
	    searchVO.setPageSize(5);
	    
	    PaginationInfo paginationInfo = new PaginationInfo();
	    paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
	    paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
	    paginationInfo.setPageSize(searchVO.getPageSize());

	    // 페이징 정보 저장
	    searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	    searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
	    searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	    // CT4 리스트 조회
	    List<?> inquiryAnswerList = inquiryAnswerService.selectCt4List(searchVO);
	    model.addAttribute("inquiryAnswerList", inquiryAnswerList);
	  

	    // CT 리스트 조회
	    List<?> inquiryList = inquiryService.selectCtList(searchVO);
	    model.addAttribute("inquiryList", inquiryList);

	    // 전체 개수 조회 및 페이지 정보 저장
	    int totCnt = inquiryService.selectCtListTotCnt(searchVO);
	    paginationInfo.setTotalRecordCount(totCnt);
	    model.addAttribute("paginationInfo", paginationInfo);

	    // 'inid'가 null이 아닐 경우 해당 값을 모델에 추가
	    if (inid != null) {
	        model.addAttribute("inid", inid);
	    }
	    return "center/inquiry/inquiryList"; // JSP 페이지 반환
	}
	
	@PostMapping("/inquiry/write") // insert 실행 - post방식
	   public String createCt(@ModelAttribute InquiryVO inquiryVO, HttpSession session, RedirectAttributes redirectAttributes, @RequestParam(defaultValue = " ") String inid) throws Exception { 
		  
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		if (memberVO == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
			return "redirect:/login";
		}
		inquiryVO.setWriter(memberVO.getEmail());
		inquiryService.insertCt(inquiryVO);
		  
		return "redirect:/inquiryList";  // 2) 전제조회 페이지로 강제이동
	}

	@PostMapping("/inquiry/delete")
	public String deleteCt(@ModelAttribute InquiryVO inquiryVO) throws Exception {
//	      1) deptSerive 의 delete 함수 실행
		inquiryService.deleteCt(inquiryVO);
	    return "redirect:/inquiryList"; // 2) 전체조회로 강제이동
	}
	   
	// 상세조회 페이지 열기
	@GetMapping("/inquiryDetail")
	public String oneononeDetail(@RequestParam("inid") String inid, Model model) throws Exception {
	   // 1) ctService의 상세조회 함수 실행
	   InquiryVO inquiryVO = inquiryService.selectCt(inid);
	   InquiryAnswerVO inquiryAnswerVO =inquiryService.selectAn(inid);
	    // 2) 모델에 담아 JSP로 전송
	    model.addAttribute("inquiryVO", inquiryVO);
	    model.addAttribute("inquiryAnswerVO", inquiryAnswerVO);
	    // 3) 상세 조회 페이지로 이동
	    return "center/inquiry/inquiryDetail";  // This should be the path to your detailed JSP view
	 }
	    	        
	@PostMapping("/inquiry/update") // update 실행 - post방식
	public String updateCt(@ModelAttribute InquiryVO inquiryVO, HttpSession session,  RedirectAttributes 
			redirectAttributes, @RequestParam(defaultValue = " ") String inid) throws Exception { 		
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		if (memberVO == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
		    return "redirect:/login";
		}
		System.out.println(inquiryVO);
		inquiryVO.setWriter(memberVO.getEmail());
		inquiryService.updateCt(inquiryVO);
			
		return "redirect:/inquiryList";  // 2) 전제조회 페이지로 강제
	}

	@GetMapping("/inquiryUpdate")
	// 게시글 ID를 파라미터로 받음
	public String updateCtList(@RequestParam("inid") String inid,  Model model) throws Exception {
		// 1) 게시글 ID로 해당 게시글 데이터 조회
		InquiryVO inquiryVO = inquiryService.selectCt(inid);
		// 2) 모델에 게시글 데이터 추가
		model.addAttribute("inquiryVO", inquiryVO);
		// 3) 수정 페이지로 이동
		return "center/inquiry/inquiryUpdate";
	}	   	   	   	   	   	   
}
