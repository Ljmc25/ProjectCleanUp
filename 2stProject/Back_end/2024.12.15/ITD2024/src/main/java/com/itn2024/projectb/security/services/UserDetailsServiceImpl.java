package com.itn2024.projectb.security.services;

import com.itn2024.projectb.vo.auth.Member;
import com.itn2024.projectb.mapper.auth.MemberMapper;
import com.itn2024.projectb.security.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberMapper.selectMember(email).orElseThrow(() -> new UsernameNotFoundException("우리회원이 아닙니다."));
        GrantedAuthority authority = new SimpleGrantedAuthority(member.getCodeName());
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(authority);
        return new MemberDto(member.getEmail(),
                member.getPassword(),
                authorities
        );
    }
}
