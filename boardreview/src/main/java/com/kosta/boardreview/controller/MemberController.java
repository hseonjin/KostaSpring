package com.kosta.boardreview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MemberController {
    @GetMapping("login") // 로그인 페이지 GET
    public String login() {
        return "login";
    }

    @GetMapping("join") // 회원가입 페이지 GET
    public String join() {
        return "join";
    }
}
