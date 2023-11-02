package com.kosta.bank.controller;

import com.kosta.bank.dto.Member;
import com.kosta.bank.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;
    @RequestMapping(value="/join", method= RequestMethod.GET)
    public String join() {
        return "join";
    }
    @RequestMapping(value="/join", method = RequestMethod.POST)
    public String join(@ModelAttribute Member mem, Model model) {
        try {
            memberService.join(mem);
            model.addAttribute("mem", mem);
            return "login";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("err", "회원가입 실패");
            return "error";
        }
    }
    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String login() {
        return "login";
    }
    @RequestMapping(value="login", method = RequestMethod.POST)
    public String login(@RequestParam("id") String id, @RequestParam("password") String password, Model model, HttpSession session) {
        try {
            Member mem = memberService.login(id, password);
            mem.setPassword("");
            session.setAttribute("id", id);
            return "makeAccount";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("err", "로그인 실패");
            return "error";
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("id");
        return "main";
    }

}
