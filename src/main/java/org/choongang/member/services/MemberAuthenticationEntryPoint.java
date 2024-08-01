package org.choongang.member.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class MemberAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        /**
         * 회원 전용 페이지로 접근한 경우 - /mapage -> 로그인 페이지 이동
         * 관리자 페이지로 접근한 경우 - 응답 코드 401, 에러페이지 출력
         */
        
        String uri = request.getRequestURI();
        if (uri.contains("/admin")) { // 관리자 페이지
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED); // 기본 에러 코드 401로 표시
        } else  { // 회원 전용 페이지
            // 로그인 하라고 한 뒤에 로그인을 하면 쿼리스트링을 붙여서 원래 있던 페이지로 돌려준다
            String qs = request.getQueryString();
            String redirectUrl = uri.replace(request.getContextPath(), "");
            if (StringUtils.hasText(qs)) {
                redirectUrl += "?" + qs;
            } // 직전에 접근했던 페이지로 돌려준다
            
            response.sendRedirect(request.getContextPath() + "/member/login?redirectUrl=" + redirectUrl);
            // 쿼리스트링을 붙여서 원래 있던 페이지로 돌려준다
        }
    }
}
