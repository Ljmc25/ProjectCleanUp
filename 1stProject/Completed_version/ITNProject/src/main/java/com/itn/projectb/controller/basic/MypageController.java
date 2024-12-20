package com.itn.projectb.controller.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itn.projectb.service.auth.MemberService;
import com.itn.projectb.service.basic.BookService;
import com.itn.projectb.service.basic.PaymentService;
import com.itn.projectb.service.basic.ScreeningService;
import com.itn.projectb.vo.basic.BookVO;
import com.itn.projectb.vo.basic.MemberVO;
import com.itn.projectb.vo.basic.ScreeningVO;
import com.itn.projectb.vo.common.Criteria;

@Controller
public class MypageController {
	
	@Autowired
	MemberService memberService;
	@Autowired
	ScreeningService screeningService;
	@Autowired
	BookService bookService;
	@Autowired
	PaymentService paymentService;
	
	@GetMapping("/mypage")
	public String mypageMain(HttpSession session,@ModelAttribute("searchVO") Criteria searchVO,Model model) {
        // 세션에서 로그인된 회원 정보 가져오기
        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
        
        // null 체크: 로그인 여부 확인
        if (memberVO == null) {
            // 로그인이 안 되어 있다면 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }
        
   //   TODO: 0) 페이징 변수에 설정 : 
 	   searchVO.setPageUnit(6); // 1페이지당 화면에 보이는 개수
 	   searchVO.setPageSize(5); // 페이지 번호를 보여줄 개수
 	   
 	//   TODO: 페이지 객체 생성
 	   PaginationInfo paginationInfo = new PaginationInfo();         // 페이징 객체
 	   paginationInfo.setCurrentPageNo(searchVO.getPageIndex());     // 현재 페이지 번호 저장
 	   paginationInfo.setRecordCountPerPage(searchVO.getPageUnit()); // 1페이지당 보일 게시물 개수
 	   paginationInfo.setPageSize(searchVO.getPageSize());           // 페이지 번호를 보여줄 개수
 	   
 	//   TODO: searchVO 객체 페이징 정보 저장
 	   searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());           // 첫페이지번호
 	   searchVO.setLastIndex(paginationInfo.getLastRecordIndex());             // 끝페이지번호
 	   searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage()); // 1페이지당 보일 게시물 개수
        
        MemberVO memberVO2 = memberService.selectMember(memberVO);
        searchVO.setEmail(memberVO2.getEmail());
        model.addAttribute("MemberVO",memberVO2);
        List<?> bookList = bookService.selectBookList(searchVO);
        model.addAttribute("BookList",bookList);
        // List<?> paymentList = paymentService.selectPaymentList(memberVO2);
        int totCnt = bookService.selectBookListTotCnt(searchVO);
 	   	paginationInfo.setTotalRecordCount(totCnt);
 		//   TODO: 페이징 객체 -> jsp 전달
 		model.addAttribute("paginationInfo", paginationInfo);
        
       
        // model.addAttribute("PaymentList",paymentList);
        
		return "mypage/mypage_main";
	}
	
	@ResponseBody
	@PostMapping("/productCencal")
	public String paymentCencal(@RequestBody BookVO bookVO2) {
		String boid = bookVO2.getBoid();
		
		BookVO bookVO = bookService.selectBookId(boid);
		ScreeningVO screeningVO = screeningService.selectScreeningScid(bookVO.getScid());
		int bNum = bookVO.getSelectNum();
		String bSeat = bookVO.getSelectSeat();

		int sNum = screeningVO.getRemainSeats();
		String sSeat = screeningVO.getReservedSeats();
		
		screeningVO.setRemainSeats(sNum+bNum);

		List<String> bArrayStrings = new ArrayList<String>();
		List<String> sArrayStrings = new ArrayList<String>();
		bArrayStrings.addAll(Arrays.asList(bSeat.split(",")));
		sArrayStrings.addAll(Arrays.asList(sSeat.split(",")));
		
		String seatsString = "";
		
		for(int i=0;i<bArrayStrings.size();i++) {
			for(int j=0;j<sArrayStrings.size();j++) {
				if(sArrayStrings.get(j).equals(bArrayStrings.get(i))) {
					sArrayStrings.remove(j);
					break;
				}
			}
		}
		
		for(int i=0;i<sArrayStrings.size();i++) {
			if(i==0) {
				seatsString+=sArrayStrings.get(i);
			}
			else {
				seatsString+=",";
				seatsString+=sArrayStrings.get(i);
			}
		}
		
		screeningVO.setReservedSeats(seatsString);
		screeningService.updateCencalData(screeningVO);
		bookService.updateBookDate(boid);
		paymentService.updatePaymentDate(boid);

		return "1";
	}
	
	@ResponseBody
	@PostMapping("/updateMember")
	public String name(@RequestBody MemberVO memberVO) {
		memberService.updateMember(memberVO);
		return "1";
	}
}
