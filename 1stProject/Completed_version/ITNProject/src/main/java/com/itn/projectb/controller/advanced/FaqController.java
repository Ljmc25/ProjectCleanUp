package com.itn.projectb.controller.advanced;

import java.util.List;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itn.projectb.service.advanced.FaqService;
import com.itn.projectb.vo.advanced.FaqVO;
import com.itn.projectb.vo.common.Criteria;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class FaqController {
	
	@Autowired
	FaqService faqService;
	
	@GetMapping("/faqList")
	public String selectCt2List(@ModelAttribute("searchVO") Criteria searchVO,Model model) throws Exception {
//	    TODO: 0) 페이징 변수에 설정 : 
		searchVO.setPageUnit(5); // 1페이지당 화면에 보이는 개수
		searchVO.setPageSize(5); // 페이지 번호를 보여줄 개수

//		TODO: 페이지 객체 생성
		PaginationInfo paginationInfo = new PaginationInfo(); // 페이징 객체
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex()); // 현재 페이지 번호 저장
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit()); // 1페이지당 보일 게시물 개수
		paginationInfo.setPageSize(searchVO.getPageSize()); // 페이지 번호를 보여줄 개수

//		TODO: searchVO 객체 페이징 정보 저장
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex()); // 첫페이지번호
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex()); // 끝페이지번호
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage()); // 1페이지당 보일 게시물 개수

		List<?> faqList = faqService.selectCt2List(searchVO);
		model.addAttribute("faqList", faqList);
			
//		TODO: 3)  Exam 테이블의 총개수(서비스 객체의 함수를 실행) : 페이지 객체 저장
		int totCnt = faqService.selectCt2ListTotCnt(searchVO);

		paginationInfo.setTotalRecordCount(totCnt);
//		TODO: 페이징 객체 -> jsp 전달
		model.addAttribute("paginationInfo", paginationInfo);
        return "center/faq/faqList";
	}
	
	// 상세조회 페이지// 열기
	@GetMapping("/faqDetail")
	public String oftenDetail(@RequestParam("faid") String faid, Model model) throws Exception {
		// 1) ctService의 상세조회 함수 실행
		FaqVO faqVO = faqService.selectCt2(faid);
		// 2) 모델에 담아 JSP로 전송
		model.addAttribute("faqVO", faqVO); 
		// 3) 상세 조회 페이지로 이동
		return "center/faq/faqDetail";  // This should be the path to your detailed JSP view
	}
	
	//관리자 페이지
	@GetMapping("/admin/faq")
	public String selectQnaList(@ModelAttribute("searchVO") Criteria searchVO, Model model) throws Exception {
	    // 페이징 변수 설정
	    searchVO.setPageUnit(5); // 1페이지당 화면에 보이는 개수
	    searchVO.setPageSize(2);  // 페이지 번호를 보여줄 개수

	    // PaginationInfo 객체 생성
	    PaginationInfo paginationInfo = new PaginationInfo();
	    paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
	    paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
	    paginationInfo.setPageSize(searchVO.getPageSize());

	    // searchVO에 페이징 정보 저장
	    searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	    searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
	    searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	    List<?> qnas = faqService.selectQnaList(searchVO);
	    model.addAttribute("qnas", qnas);

	    int totCnt = faqService.selectQnaListTotCnt(searchVO);
	    paginationInfo.setTotalRecordCount(totCnt);
	    
	    // 총 페이지 수 계산
	    int totalPages = (int) Math.ceil((double) totCnt / searchVO.getPageUnit());
	    searchVO.setTotalPages(totalPages); // criteria에 totalPages 추가

	    // paginationInfo 전달
	    model.addAttribute("paginationInfo", paginationInfo);
	    model.addAttribute("criteria", searchVO);

	    return "admin/faq/qna_all";
	}

	@GetMapping("/qna/addition")
	public String createQnaView(Model model) {
		model.addAttribute("qnaVO", new FaqVO());
		return "admin/faq/add_qna";
	}
	
	@PostMapping("/qna/add")
	public String createQna(@ModelAttribute FaqVO qnaVO
			, BindingResult bindingResult) throws Exception {
		qnaVO.setWriter("joonbyungsuh@naver.com");
		faqService.insertQna(qnaVO);
		
		
		return "redirect:/admin/faq";
	}
	
	@GetMapping("/qna/edition/{faid}")
	public String updateQnaView(@PathVariable String faid
			, Model model) throws Exception {
		FaqVO qnaVO = faqService.selectQna(faid);
		model.addAttribute("qnaVO", qnaVO);
		return "admin/faq/update_qna";
	}
	
	@PostMapping("/qna/edit")
	public String updateQna(@ModelAttribute FaqVO qnaVO) throws Exception {
		faqService.updateQnaVO(qnaVO);  // QnaVO 안의 faid를 이용하여 업데이트
	    return "redirect:/admin/faq";  // 업데이트 후 Q&A 목록 페이지로 리다이렉트
	}

	
	@PostMapping("/qna/delete")
	public String deleteQna(@ModelAttribute FaqVO qnaVO) throws Exception {
		faqService.deleteQnaVO(qnaVO);
		return "redirect:/admin/faq";
	}
}
