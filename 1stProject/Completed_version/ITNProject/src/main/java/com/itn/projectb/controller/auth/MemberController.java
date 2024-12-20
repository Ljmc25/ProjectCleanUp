/**
 * 
 */
package com.itn.projectb.controller.auth;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.itn.projectb.service.auth.MemberService;
import com.itn.projectb.vo.basic.MemberVO;
import com.itn.projectb.vo.common.Criteria;

import lombok.extern.log4j.Log4j;

/**
 * @author
 *
 */
@Log4j
@Controller

public class MemberController {

	@Autowired
	MemberService memberService;

	// 로그인 화면
	@GetMapping("/login")
	public String loginView() {
		return "sign/signin";
	}

	// 로그인 진행
	@PostMapping("/loginProcess")
	public String login(@ModelAttribute MemberVO loginVO, HttpServletRequest request) throws Exception {
		log.info("1 : " + loginVO);
		MemberVO memberVO = memberService.authenticateMember(loginVO);
		log.info("2");
		if (memberVO == null) {
			throw new Exception("없는 회원입니다.");
		}
		// 인증 OK(DB 에 유저가 있으면) : 세션에 email/password 객체(memberVO) 넣기
		request.getSession().setAttribute("memberVO", memberVO);
		return "redirect:/main"; // 메인 페이지 이동
	}

//	회원가입 화면
	@GetMapping("/register")
	public String registerView() {
		return "sign/register";
	}


//	회원가입 
	@PostMapping("/register/addition")
	public String register(@ModelAttribute("memberVO") MemberVO memberVO, 
												@RequestParam("passwordConfirm") String passwordConfirm, 																					
																					Model model) throws Exception {
		log.info("테스트 : " + memberVO);
//		이미 가입된 회원 확인
		MemberVO memberVO2 = memberService.authenticateMember(memberVO);
		if (memberVO2 != null) {
			 // 이메일이 이미 존재하는 경우
	        model.addAttribute("emailMessage", "이미 가입되어 있습니다.");  // 메시지 추가
	        // 다시 등록 페이지로 이동
			return "redirect:/register";
		}else {
			model.addAttribute("emailMessage", "사용 가능한 이메일입니다.");
		}
		// 비밀번호 확인
	    if (!memberVO.getPassword().equals(passwordConfirm)) {
	        model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
	        model.addAttribute("memberVO", memberVO);
	        return "sign/register"; // 비밀번호 불일치 시 등록 페이지로 이동
	    }

		memberService.registerMember(memberVO);
		
		return "redirect:/main";
	}
// 이메일 중복 확인
	@PostMapping(value = "/checkEmail", produces = "application/json; charset=UTF-8")	
	@ResponseBody
	public ResponseEntity<String> checkEmail(@RequestBody MemberVO memberVO) throws Exception {		 
		
		boolean isDuplicate = memberService.checkEmailDuplicate(memberVO);
		if (isDuplicate) {
			return ResponseEntity.ok("중복된 이메일입니다.");
		} else {
			return ResponseEntity.ok("사용 가능한 이메일 입니다.");
		}
	}

//	로그 아웃 메뉴 클릭시 실행
	@GetMapping("/logout")
	public String logout(HttpSession httpSession) {
		httpSession.removeAttribute("memberVO");
		httpSession.invalidate();
		return "redirect:login";
	}
	
	@GetMapping("/admin/member")
	public String selectUserList(
	        @ModelAttribute("searchVO") Criteria searchVO,
	        Model model,
	        @RequestParam(defaultValue = "1") int pageIndex) throws Exception {

	    int pageSize = searchVO.getPageSize(); // 페이지당 데이터 수
	    int offset = (pageIndex - 1) * pageSize; // SQL OFFSET 계산

	    List<MemberVO> userList = memberService.getUserList(offset, pageSize); // 사용자 목록 조회
	    model.addAttribute("users", userList); // 사용자 목록 모델에 추가
	    model.addAttribute("totalPages", (int) Math.ceil((double) memberService.getTotalUserCount() / pageSize)); // 전체 페이지 수 계산하여 추가

	    return "admin/member/user_list"; // JSP 페이지 이름 반환
	}
}
