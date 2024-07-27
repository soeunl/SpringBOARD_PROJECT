package org.choongang.global.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig { // Spring Security 설정을 담당하는 클래스

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 설정 무력화를 시킬 것임, 기본 설정 초기화
        // 이렇게 하면 시큐리티 설정 무력화가 되어서 인증하는 로그인 페이지가 더이상 나오지 않게 된다.
        return http.build();
    }

    public PasswordEncoder passwordEncoder() {
        // 스프링 시큐리티에는 비크립트 비밀번호 해시화 기능이 포함되어 있다.
        // 인코딩도 해주고 비밀번호 검증도 해준다.
        return new BCryptPasswordEncoder();
    }
}
