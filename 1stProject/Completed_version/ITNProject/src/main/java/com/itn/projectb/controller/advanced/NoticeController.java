package com.itn.projectb.controller.advanced;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.itn.projectb.service.advanced.NoticeService;
import com.itn.projectb.vo.advanced.NoticeVO;
import com.itn.projectb.vo.common.Criteria;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller

public class NoticeController {

	@Autowired
	NoticeService noticeService;

	@GetMapping("/noticeAll")
	public String selectCt3List(@ModelAttribute("searchVO") Criteria searchVO, Model model) throws Exception {
//		TODO: 0) 페이징 변수에 설정 : 
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

		List<?> noticeList = noticeService.selectCt3List(searchVO);
		model.addAttribute("noticeList", noticeList);
			
//		TODO: 3)  Exam 테이블의 총개수(서비스 객체의 함수를 실행) : 페이지 객체 저장
		int totCnt = noticeService.selectCt3ListTotCnt(searchVO);

		paginationInfo.setTotalRecordCount(totCnt);
//		TODO: 페이징 객체 -> jsp 전달
		model.addAttribute("paginationInfo", paginationInfo);
		return "center/notice/noticeList";    
	}
	
	// 상세조회 페이지// 열기
	@GetMapping("/noticeDetail")
	public String gongjiDetail(@RequestParam("noid") String noid, Model model) throws Exception {
		// 1) ctService의 상세조회 함수 실행
		NoticeVO noticeVO = noticeService.selectCt3(noid); 
		// 2) 모델에 담아 JSP로 전송
		model.addAttribute("noticeVO", noticeVO);
		// 3) 상세 조회 페이지로 이동
		return "center/notice/noticeDetail";  // This should be the path to your detailed JSP view
	}	
	
	// 관리자 페이지
	@GetMapping("/admin/notice")
	public String selectNotiList(
	        @ModelAttribute("searchVO") Criteria searchVO,
	        Model model,
	        @RequestParam(defaultValue = "1") int pageIndex,
	        HttpSession session) throws Exception {

	    // pageIndex에 따라 firstIndex를 계산
	    searchVO.setFirstIndex((pageIndex - 1) * searchVO.getPageSize());
	    searchVO.setRecordCountPerPage(searchVO.getPageSize()); // 페이지당 레코드 수 설정

	    // 알림 데이터 가져오기
	    List<NoticeVO> notiList = noticeService.selectNotiList(searchVO); // 알림 데이터 가져오기

	    // 세션에서 랜덤 숫자 가져오기
	    @SuppressWarnings("unchecked") // 경고 억제
	    Map<Integer, Integer> randomNumbers = (Map<Integer, Integer>) session.getAttribute("randomNumbers");
	    if (randomNumbers == null) {
	        randomNumbers = new HashMap<>();
	        for (int i = 0; i < notiList.size(); i++) {
	            randomNumbers.put(i, (int)(Math.random() * 1000) + 1); // 1~1000 사이의 랜덤 숫자
	        }
	        session.setAttribute("randomNumbers", randomNumbers); // 세션에 저장
	    }

	    model.addAttribute("notis", notiList);
	    model.addAttribute("totalPages", (int) Math.ceil((double) noticeService.getTotalNotiCount() / searchVO.getPageSize())); // 총 페이지 수 설정

	    return "admin/notice/noti_all"; // JSP 페이지 이름
	}

	@GetMapping("/noti/addition")
    public String createAddNoti() {
        return "admin/notice/noti_add";
    }
	
	@PostMapping("/AddNoti")
	public String insertNoti(
	        @RequestParam("announcementTitle") String title,
	        @RequestParam("announcementContent") String contents,
	        @RequestParam("email") String writer,
	        @RequestParam("moviePoster") MultipartFile file) throws Exception {

	    // 파일이 비어 있지 않다면 URL 경로 생성
	    String fileUrl = null;
	    if (!file.isEmpty()) {
	        // 파일 이름을 가져와서 URL 형식으로 경로 설정
	        String fileName = file.getOriginalFilename();
	        fileUrl = "/resources/images/notice/" + fileName; // 서버에 저장하는 대신 URL만 생성
	    }

	    // NotiVO 객체 생성, 라이터는 "관리자"
	    NoticeVO noti = new NoticeVO();
	    noti.setTitle(title);
	    noti.setContents(contents);
	    noti.setFileUrl(fileUrl);
	    noti.setWriter(writer);
	    noticeService.insertNoti(noti); // 서비스 호출
	    return "redirect:/admin/notice"; // 공지사항 리스트 페이지로 리다이렉트
	}


	   @GetMapping("/editNoti/{noid}")
	    public String editNoti(@PathVariable("noid") String noid, Model model) {
		   NoticeVO noti = noticeService.findById(noid);
	       model.addAttribute("noti", noti);
	       return "admin/notice/noti_edit"; // JSP 페이지 이름
	    }

	   @PostMapping("/UpdateNoti")
	   public String updateNoti(@ModelAttribute NoticeVO notiVO, @RequestParam("moviePoster") MultipartFile file) {

	       // 파일 처리
	       if (!file.isEmpty()) {
	           String originalFileName = file.getOriginalFilename();
	           String fileUrl = "resources/img/" + originalFileName; 
	           notiVO.setFileUrl(fileUrl);  // 파일 URL 설정
	       } else {
	           // 파일이 선택되지 않은 경우 기존 URL 유지
	    	   NoticeVO existingNoti = noticeService.findById(notiVO.getNoid());
	           notiVO.setFileUrl(existingNoti.getFileUrl());
	       }

	       // NotiVO 업데이트 처리
	       noticeService.updateNoti(notiVO);

	       return "redirect:/admin/notice"; 
	   }

	   @PostMapping("/noti/deleteNoti")
	    public String deleteNoti(@RequestParam("noid") String noid) {
		   noticeService.deleteNoti(noid); // 삭제 서비스 호출
	        return "redirect:/admin/notice"; // 삭제 후 목록으로 리다이렉트
	    }
}
