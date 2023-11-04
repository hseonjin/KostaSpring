package com.kosta.boardreview.controller;

import com.kosta.boardreview.dto.Member;
import com.kosta.boardreview.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class MemberController {
    @Autowired
    private MemberService service;

    @GetMapping("login") // 로그인 페이지 GET
    public String login() {
        return "login";
    }

    @GetMapping("join") // 회원가입 페이지 GET
    public String join() {
        return "join";
    }

    @PostMapping("join")
    public ModelAndView join(@ModelAttribute Member member) {
        ModelAndView mav = new ModelAndView();
        try {
            service.join(member);
            mav.addObject("member", member); // 회원가입된 회원 정보를 유지하고 view에 표시하기 위한 용도
            mav.setViewName("login"); // 처리 후 로그인 페이지 출력
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("err", "회원가입 실패");
            mav.setViewName("error");
        }
        return mav;
    }

    // 회원가입 중복확인 => join.jsp에서 ajax를 통해 post 요청
    @PostMapping("idcheck")
    @ResponseBody
    public String idCheck(@RequestParam("id") String id) {
        try {
            Member member = service.userInfo(id);
            if(member==null) return "notexist"; // 아이디 사용 가능한 경우("notexist" 반환)
            return "exist"; // 아이디 사용 불가능한 경우("exist" 반환)
        } catch (Exception e) {
            e.printStackTrace();
            return "exist"; // catch문 발생할 때도 "exist"를 보내는 이유는 예외 처리 코드를 간소화하기 위함
        }
    }

    // 로그인
    @PostMapping("login")
    public ModelAndView login(@RequestParam("id") String id, @RequestParam("password") String password, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        try {
            Member member = service.login(id, password);
            session.setAttribute("user", member);
            mav.addObject("member", member);
            mav.setViewName("main");
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("err", "로그인 실패");
            mav.setViewName("error");
        }
        return mav;
    }

    // 로그아웃
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return ("main");
    }
}
