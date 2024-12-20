package com.itn2024.projectb.controller.auth;


import com.itn2024.projectb.service.auth.MemberService;
import com.itn2024.projectb.vo.auth.Member;
import com.itn2024.projectb.vo.common.Criteria;
import com.itn2024.projectb.vo.dto.ResultDto;
import com.itn2024.projectb.vo.dto.UserReqDto;
import com.itn2024.projectb.vo.dto.UserResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author : KTE
 * @fileName : MemberController
 * @since : 24. 11. 12.
 * description :
 */
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/api/auth/member")
    public ResponseEntity<?> selectMemberList(Criteria searchVO) {
        List<?> members = memberService.selectMemberList(searchVO);

        ResultDto resultDto = new ResultDto(members, searchVO.getTotalItems());

        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    //    회원가입(insert)
//    객체로 전달
    @PostMapping("/api/auth/register")
    public ResponseEntity<?> insert(@RequestBody Member member) {
        memberService.insert(member);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    로그인 : Post 방식
    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(
            @RequestBody UserReqDto userReqDto) {
//        클래스위에 커서 두고 : CTRL + B
        UserResDto userResDto = memberService.login(userReqDto);

        return new ResponseEntity<>(userResDto, HttpStatus.OK);
    }

    @GetMapping("/api/auth/member/{email}")
    public ResponseEntity<?> selectMember(@PathVariable String email) {
        Optional<Member> member = memberService.selectMember(email);
        if (member.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
//        TODO: dept.get() => 옵셔널에서 꺼내기 함수
        return new ResponseEntity<>(member.get(), HttpStatus.OK);
    }

    @PutMapping("/api/auth/member/{email}")
    public ResponseEntity<?> update(@PathVariable String email, @RequestBody Member member) {
        memberService.update(member);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/auth/member/deletion/{email}")
    public ResponseEntity<?> delete(@PathVariable String email) {
        memberService.delete(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
