package com.kosta.api.controller;

import com.kosta.api.dao.UserInfo;
import com.kosta.api.service.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class Login {
    @Autowired
    private KakaoService kakaoService;
    // 카카오 로그인 인가 코드 요청
    @GetMapping("kakao")
    public String kakaoLogin() {
        return "kakaoLogin";
    }

    // 카카오 로그인 토큰 발급 요청
    @GetMapping("kakaologin")
    public ModelAndView kakaoLogin(@RequestParam("code") String code) {
        ModelAndView mav = new ModelAndView();
        try {
            UserInfo userInfo = kakaoService.kakaoLogin(code);
            mav.addObject("userInfo", userInfo);
            mav.setViewName("userInfo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }
}