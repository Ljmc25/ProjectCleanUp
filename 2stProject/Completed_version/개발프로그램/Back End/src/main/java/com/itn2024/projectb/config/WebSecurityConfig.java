package com.itn2024.projectb.config;

import com.itn2024.projectb.security.jwt.AuthTokenFilter;
import jakarta.servlet.DispatcherType;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
public class WebSecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter(); // 개발자가 작성한 웹토큰 인증필터 생성자 함수
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                "/images/**",
                "/css/**",
                "/js/**"
        );
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults());
        http.csrf((csrf) -> csrf.disable());
        http.sessionManagement(sessionManagement
                -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.formLogin(req -> req.disable());

        http.authorizeHttpRequests(
                req -> req.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers(
                                "/"  // 로그인, 회원가입 등 auth 관련 페이지 모두 허용
                                                        ).permitAll()  // 위에 나열된 경로는 모두 허용
                        .anyRequest().permitAll()  // 나머지 모든 요청은 인증 필요
        );

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
