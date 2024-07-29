package org.choongang.member.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.choongang.member.validator.JoinValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final JoinValidator joinValidator;

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

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login() {
        return "front/member/login";
    }
}
