package com.kosta.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {
    // main 페이지를 열어주는 메소드
    @RequestMapping(value="/join", method= RequestMethod.GET)
    public String main() {
        return "join";
    }
    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String login() {
        return "login";
    }

}
