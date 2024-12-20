package com.itn.projectb.controller.basic;

import java.util.List;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itn.projectb.service.basic.ExamService;
import com.itn.projectb.vo.basic.ExamVO;
import com.itn.projectb.vo.common.Criteria;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class ExamController {

	@Autowired
	ExamService examService;

	@GetMapping("/exam")
	public String ExamPage(@ModelAttribute("searchVO") Criteria searchVO, Model model) {
//        TODO: 0) 페이징 변수에 설정 : 
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

		List<?> examList = examService.selectExamList(searchVO);
		model.addAttribute("examList", examList);
		
//		TODO: 3)  Exam 테이블의 총개수(서비스 객체의 함수를 실행) : 페이지 객체 저장
		int totCnt = examService.selectExamListTotCnt(searchVO);

		paginationInfo.setTotalRecordCount(totCnt);
//		TODO: 페이징 객체 -> jsp 전달
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "exam/exam_all";
	}
	
	@GetMapping("/exam/view/add")
	public String createExamView() {
		return "exam/exam_add";
	}
	
	@PostMapping("/exam/add")
	public String createExam(@RequestParam(defaultValue = "") String title,
			@RequestParam(defaultValue = "") String contents) {
		ExamVO examVO = new ExamVO(title,contents);
		examService.insertExam(examVO);
		return "redirect:/exam";
	}
	
	@GetMapping("/exam/view")
	public String examView(@RequestParam String uuid, Model model) {
		ExamVO examVO = examService.selectExam(uuid);
		model.addAttribute("examVO",examVO);
		return "exam/exam_view";
	}
	
	@GetMapping("/exam/view/update")
	public String updateView(@RequestParam String uuid, Model model) {
		ExamVO examVO = examService.selectExam(uuid);
		model.addAttribute("examVO",examVO);
		return "exam/exam_update";
	}
	
	@PostMapping("/exam/update")
	public String update(@RequestParam String uuid, @ModelAttribute ExamVO examVO) {
		examService.updateExam(examVO);
		return "redirect:/exam";
	}
	
	@PostMapping("/exam/delete")
	public String delete(@ModelAttribute ExamVO examVO) {
		examService.deleteExam(examVO);
		return "redirect:/exam";
	}
	
	
}
