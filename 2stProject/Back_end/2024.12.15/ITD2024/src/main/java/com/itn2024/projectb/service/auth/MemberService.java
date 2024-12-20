package com.itn2024.projectb.service.auth;

import com.itn2024.projectb.mapper.auth.MemberMapper;
import com.itn2024.projectb.security.jwt.JwtUtils;
import com.itn2024.projectb.vo.auth.Member;
import com.itn2024.projectb.vo.common.Criteria;
import com.itn2024.projectb.vo.dto.UserReqDto;
import com.itn2024.projectb.vo.dto.UserResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author : KTE
 * @fileName : MemberService
 * @since : 24. 11. 11.
 * description :
 */
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<?> selectMemberList(Criteria searchVO) {
        List<?> page = memberMapper.selectMemberList(searchVO);

        int count = memberMapper.selectMemberListTotCnt(searchVO);
        searchVO.setTotalItems(count);

        return page;
    }

    //    인증/권한 체크 클래스(id/pwd 체크)
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtUtils jwtUtils;   // 웹토큰

    //    회원가입(insert) : 패스워드(암호화)
    public void insert(Member member) {
//        1) 유효성체크 : 이사람이 이미 가입된 사람인지 확인? 통과
        if(memberMapper.existsById(member.getEmail()) > 0) {
            throw new NoSuchElementException("같은 이메일의 회원이 있습니다.");
        }
//        2) 회원가입(insert) :
//        TODO: 암호화 적용 필요(라이브러리 : Bcrypt (스프링시큐리티 안에 포함))
//          Bcrypt : 단방향 암호화(해싱 암호화) : 암호화 되고 복호화안되는것
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberMapper.insert(member);
    }

    //    TODO: 로그인 , DTO 필요(택배상자)
    public UserResDto login(UserReqDto userReqDto) {
//        (1) id/pwd 인증(확인) : 통과
//        TODO: (참고) .authenticate() => UserDetailsServiceImple 상세조회 함수가 실행됨
//              (참고) Authentication 객체 == 인증된객체 (유저)
        Authentication authentication
                = authenticationManagerBuilder.getObject().authenticate(
                new UsernamePasswordAuthenticationToken(userReqDto.getEmail(),
                        userReqDto.getPassword())
        );
//        (2) 통과된 유저들 => 필통(홀더)에 보관 : 인증완료
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        (3) 웹토큰 발급(벡엔드) : 카드패스(호텔카드키)
        String jwt = jwtUtils.generateJwtToken(authentication);
//        (4) 혹시 권한정보가 있으면 그것도 정의(생략) : ROLE_ADMIN,ROLE_USER
        String codeName = new ArrayList(authentication.getAuthorities()).get(0).toString();
//        (5) 택배상자(DTO)에 담기 : 1) 웹토큰, 2) 유저정보
//                => 프론트로 전송
        return new UserResDto(
                jwt,                   // 웹토큰
                userReqDto.getEmail(), // 이메일(로그인 ID)
                codeName               // 권한명
        );
    }

    public Optional<Member> selectMember(String email) {
        return memberMapper.selectMember(email);
    }

    public void update(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberMapper.update(member);
    }

    public void delete(String email) {
        memberMapper.delete(email);
    }

}
