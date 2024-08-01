package org.choongang.global.configs;

import org.choongang.member.services.LoginFailureHandler;
import org.choongang.member.services.LoginSuccessHandler;
import org.choongang.member.services.MemberAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableMethodSecurity
@EnableWebSecurity // 웹에 대한 기본적인 권한이 자동활성화된다
@Configuration
public class SecurityConfig { // Spring Security 설정을 담당하는 클래스

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 설정 무력화를 시킬 것임, 기본 설정 초기화
        // 이렇게 하면 시큐리티 설정 무력화가 되어서 인증하는 로그인 페이지가 더이상 나오지 않게 된다.
        // 시큐리티의 설정들을 여기에 넣어준다

        /* 도메인 특화 방식으로 바뀌어져 있다.*/

        /* 로그인, 로그아웃 S */
        http.formLogin(f -> { // 로그인 처리
            f.loginPage("/member/login")
                    .usernameParameter("email").passwordParameter("password")
//                    .successForwardUrl("/") // 성공시 이동할 주소
//                    .failureUrl("/member/login?error=true"); // 실패시 이동할 주소
                    .failureHandler(new LoginFailureHandler())
                    .successHandler(new LoginSuccessHandler());
        });

        http.logout(f -> {
           f.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                   .logoutSuccessUrl("/member/login");
        });
        /* 로그인, 로그아웃 E */

        /* 인가(접근 통제) 설정 S */
        http.authorizeHttpRequests(c -> {
            /*
            c.requestMatchers("/member/**").anonymous()
                    .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                    .anyRequest().authenticated();
            */
            c.requestMatchers("/mypage/**").authenticated() // 회원 전용
                    .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                    .anyRequest().permitAll();
        });

        // 무엇인가 발생하면 상세한 처리를 한다
        http.exceptionHandling(c -> {
            c.authenticationEntryPoint(new MemberAuthenticationEntryPoint());
        });

        /* 인가(접근 통제) 설정 E */


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 스프링 시큐리티에는 비크립트 비밀번호 해시화 기능이 포함되어 있다.
        // 인코딩도 해주고 비밀번호 검증도 해준다.
        return new BCryptPasswordEncoder();
    }
}
