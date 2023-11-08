package com.kosta.api.controller;

import com.kosta.api.dto.AnimalClinic;
import com.kosta.api.dto.ElecCar;
import com.kosta.api.dto.PageInfo;
import com.kosta.api.service.ElecCarApiService;
import com.kosta.api.service.SeoulApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarApiController {
    @Autowired
    private ElecCarApiService service;

    @GetMapping(value = {"", "/{page}"})
    public ModelAndView elecCarList(@PathVariable(required=false) Integer page) {
        PageInfo pageInfo = new PageInfo();
        if(page!=null) {// url에 page 값이 없다면 기본값 1로 지정
            pageInfo.setCurPage(page);
        } else {
            pageInfo.setCurPage(1);
        }
        ModelAndView mav = new ModelAndView();
        try {
            List<ElecCar> eleList = service.elecCarList(pageInfo);
            mav.addObject("eleList", eleList);
            mav.addObject("pageInfo", pageInfo);
            mav.setViewName("eleccar");
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("err", "전기차 충전소 운영정보 조회 실패");
            mav.setViewName("error");
        }
        return mav;
    }
}
