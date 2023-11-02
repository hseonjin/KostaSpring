package com.kosta.board.controller;

import com.kosta.board.dto.Member;
import com.kosta.board.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    /**
     * Simply selects the home view to render by returning its name.
     */
    @Autowired
    private MemberService memberService;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(){return "login";}

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("id") String id, @RequestParam("password") String password, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        try {
            Member mem = memberService.login(id, password);
            mav.addObject("mem", mem);
            mav.setViewName("main");
            session.setAttribute("user", mem);
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("err", "로그인 실패");
        }
        return mav;
    }

    @RequestMapping(value="/join", method = RequestMethod.GET)
    public String join(){return "join";}

    @RequestMapping(value="/join", method = RequestMethod.POST)
    public String join(@ModelAttribute Member mem, Model model){
        try {
            memberService.join(mem);
            model.addAttribute("mem", mem);
            return ("login");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("err", "회원가입 실패");
            return ("error");
        }
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return ("main");
    }
}
