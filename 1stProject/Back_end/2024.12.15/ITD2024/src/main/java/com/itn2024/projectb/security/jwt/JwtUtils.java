package com.itn2024.projectb.security.jwt;

import com.itn2024.projectb.security.dto.MemberDto;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtUtils {

    @Value("${ITN2024.app.jwtSecret}")
    private String jwtSecret;                  // 비밀키
    @Value("${ITN2024.app.jwtExpirationMs}")
    private int jwtExpirationMs;               // 만료시간(10분)

//    웹토큰 발급 함수
    public String generateJwtToken(Authentication authentication) {
        MemberDto memberDto = (MemberDto) authentication.getPrincipal(); // 스프링시큐리티 필통에서 인증유저 가져오기
        return Jwts.builder()                            // 웹토큰 발급
                .setSubject((memberDto.getEmail()))      // 웹토큰 : 이메일 넣기(로그인 ID)
                .setIssuedAt(new Date())                 // 발급일
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) // 만료시간(10분)
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // 암호화 적용 서명
                .compact();                              // 토큰 생성
    }

//    (선택) 웹토큰에서 로그인 ID(email) 꺼내기 함수
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)         // 비밀키 넣기
                .parseClaimsJws(token)            // 웹토큰이름
                .getBody()                        // 내용(Body)에 접근
                .getSubject();                    // 주제(유저ID(이메일))
    }

//    웹토큰 유효한지 확인 함수 (위조 등)
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken); // 웹토큰 검증 ?
            return true;
        } catch (SignatureException e) {
            log.error("디지털서명이 훼손되었습니다.: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("웹토큰 유효하지 않습니다.: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("웹토큰 만료되었습니다. : {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("웹토큰을 지원하지 않습니다.: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("웹토큰 내용이 비었습니다. : {}", e.getMessage());
        }

        return false;
    }
}
