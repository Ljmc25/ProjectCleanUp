package com.itn2024.projectb.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author : KTE
 * @fileName : MemberDto
 * @since : 24. 11. 13.
 * description : TODO: 스프링시큐리티 사용 DTO
 *                => 인증완료 => 필통(홀더)에 관리(MemberDto 유저객체)
 */
@Setter
@Getter
@ToString
public class MemberDto extends User {
    private String email; // 로그인 ID

    public MemberDto(String email, String password, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
        this.email = email;
    }
}
