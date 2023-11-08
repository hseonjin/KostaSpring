package com.kosta.api.controller;

import com.kosta.api.dto.UserInfo;
import com.kosta.api.service.KakaoLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KakaoLoginController {
    @Autowired
    private KakaoLoginService service;
    @GetMapping("kakao")
    public String kakao() {return "kakaologin";}

    @GetMapping("kakaologin")
//    @ResponseBody
    public ModelAndView kakaoLogin(@RequestParam("code") String code) {
        ModelAndView mav = new ModelAndView();
        try {
            UserInfo userInfo = service.kakaoLogin(code);
            mav.addObject("userInfo", userInfo);
            mav.setViewName("userinfo");
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("err", "카카오 로그인 실패");
            mav.setViewName("error");
        }
        return mav;
    }
}
