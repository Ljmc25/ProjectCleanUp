package com.itn.projectb.controller.basic;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itn.projectb.service.basic.BookService;
import com.itn.projectb.service.basic.ProductService;
import com.itn.projectb.service.basic.ScreeningService;
import com.itn.projectb.vo.basic.BookVO;
import com.itn.projectb.vo.basic.MemberVO;
import com.itn.projectb.vo.basic.PaymentVO;
import com.itn.projectb.vo.basic.ProductVO;
import com.itn.projectb.vo.basic.ScreeningVO;

@Controller
public class ReserveController {
	
	@Autowired
	ProductService productService;
	@Autowired
	ScreeningService screeningService;
	@Autowired
	BookService bookService;

    // 영화 예매 페이지
    @GetMapping("/reserve")
    public String reservechoice(@RequestParam(defaultValue = "") String title, Model model) {
        // 전체 영화 목록을 가져와서 모델에 추가
    	model.addAttribute("title",title);
        return "reserve/reserve"; // JSP 페이지로 이동
    }
    
    //예매에서 scid 뿌려서 좌석선택 정보 받기
    @GetMapping("/reserve/select1") // 경로를 지정합니다.
    public String selectSeat(HttpSession session, RedirectAttributes redirectAttributes, @RequestParam("scid") String scid, Model model) {
    	
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		if (memberVO == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
			return "redirect:/login";
		}
		
    	 // 세션에서 bookVO와 paymentVO 가져오기
    	BookVO bookVo = (BookVO) session.getAttribute("bookVO");
        PaymentVO paymentVo = (PaymentVO) session.getAttribute("paymentVO");
    	
    	 // SCID로 상영 정보 조회
        ScreeningVO screeningInfo = screeningService.getScreeningById(scid);
        model.addAttribute("screeningInfo", screeningInfo);

        
        ProductVO productVO = productService.selectProduct(screeningInfo);
        model.addAttribute("ProductVO", productVO);
                       
        model.addAttribute("bookVO", bookVo);
        model.addAttribute("paymentVO", paymentVo);
        
        return "reserve/select_seat"; // JSP 페이지로 이동
    }
    
    //결제페이지 관련로직
    @GetMapping("/reserve/payment")
    public String paymentpage(@ModelAttribute("BookVO") BookVO bookVO, Model model) {
    	    	  	
    	bookService.insertBook(bookVO);
    	String boid = bookVO.getBoid();
    	
    	BookVO bookvo = bookService.selectBookId(boid);	

    	// 조회된 BookVO의 selectSeat 값을 ScreeningVO의 reservedSeats에 설정합니다.
    	ScreeningVO screeningVO = new ScreeningVO();
    	screeningVO.setReservedSeats(bookvo.getSelectSeat());  // BookVO에서 선택된 좌석을 ScreeningVO에 설정
    	
    	// SCID 값을 BookVO 또는 다른 데이터에서 가져와 설정해야 합니다.
    	screeningVO.setScid(bookvo.getScid());  // SCID 값을 가져와서 설정
    	      
    	 // 5. 현재 남은 좌석 수를 가져와서 계산
    	int selectNum = bookvo.getSelectNum(); // BOOKVO에서 SELECT_NUM을 가져옴
        ScreeningVO currentScreening = screeningService.getScreeningById(screeningVO.getScid());
        int remainSeats = currentScreening.getRemainSeats(); // 현재 남은 좌석 수 가져오기
        int updatedRemainSeats = remainSeats - selectNum; // 새로운 남은 좌석 수 계산
        screeningVO.setRemainSeats(updatedRemainSeats); // 업데이트할 REMAIN_SEATS 설정
    	
        screeningService.updateReservedSeat(screeningVO);

        screeningService.updateRemainSeats(screeningVO); 
        
        model.addAttribute("bookvo",bookvo);
    	
        return "payment/payment";   	
    }
}
