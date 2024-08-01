package org.choongang.member.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.choongang.global.exceptions.ExceptionProcessor;
import org.choongang.member.MemberInfo;
import org.choongang.member.services.MemberSaveService;
import org.choongang.member.validator.JoinValidator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@SessionAttributes("requestLogin")
@Slf4j
public class MemberController implements ExceptionProcessor { // 인터페이스로 설정한 예외처리 프로세서

    private final JoinValidator joinValidator;
    private final MemberSaveService memberSaveService;

    @ModelAttribute // 세션 범위 내에서 속성이 추가된다
    public RequestLogin requestLogin() {
        return new RequestLogin();
    }

    @GetMapping("/join")
    public String Join(@ModelAttribute RequestJoin form) {

        return "front/member/join"; // front는 PC mobile은 모바일
    }

    @PostMapping("/join")
    public String joinPs(@Valid RequestJoin form, Errors errors) {
        // errors 객체 위치 잘 맞추기
        // 커맨드 객체. EL식 속성으로 넘어온다.
        // RequestJoin form 이 데이터를 검증하려고 하는 것이다.

        joinValidator.validate(form, errors);

        if (errors.hasErrors()) {
            // reject, rejectValue가 한번이라도 호출되면 true
            // 검증 실패 시 넘어가는 것이 아니라 다시 양식을 보여준다.
            // 이상이 없을 때만 아래쪽 return "redirect:/member/login"으로 넘어간다
            return "front/member/join";
        }

        memberSaveService.save(form); // 회원 가입 처리

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login(@Valid @ModelAttribute RequestLogin form, Errors errors) {
        String code = form.getCode();

        if (StringUtils.hasText(code)) {
            errors.reject(code, form.getDefaultMessage());

            // 비번 만료인 경우 비번 재설치 페이지 이동
            if (code.equals("CredentialsExpired.Login")) {
                return "redirect:/member/password/reset";
            }
        }
        return "front/member/login";
    }

    @ResponseBody
    @GetMapping("/test")
    public void test(Principal principal) {
        log.info("로그인 아이디: {}", principal.getName());
    }

    @ResponseBody
    @GetMapping("/test2")
    public void test2(@AuthenticationPrincipal MemberInfo memberInfo) {
        log.info("로그인 회원 : {}", memberInfo.toString());
    }

    @ResponseBody
    @GetMapping("/test3")
    public void test3() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("로그인 상태: {}", authentication.isAuthenticated());
        if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof MemberInfo) { // 로그인 상태 - UserDetails 구현체(getPrincipal())
            ;
            MemberInfo memberInfo = (MemberInfo) authentication.getPrincipal();
            log.info("로그인 회원: {}", memberInfo.toString());
        } else { // 미로그인 상태 - String / anonymousUser (getPrincipal())
            log.info("getPrincipal(): {}", authentication.getPrincipal());
        }
    }
}
