package org.choongang.member.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/join")
    public String Join() {
        return "front/member/join"; // front는 PC mobile은 모바일
    }

    @PostMapping("/join")
    public String joinPs() {
        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login() {
        return "front/member/login";
    }
}
