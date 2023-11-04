package com.kosta.boardreview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/") // 기본 루트
public class BoardController {
    @GetMapping("/") // 메인 페이지 GET
    public String main(){
        return "main";
    } // view 이름을 작성하는 것

    @GetMapping("boardlist") // 게시판 페이지 GET
    public String boardList() { return "boardlist"; }

    @GetMapping("writeform") // 게시글 작성 페이지 GET
    public String writeForm() { return "writeform"; }

    @GetMapping("detailform") // 게시글 상세 페이지 GET
    public String detailForm() { return "detailform"; }

    @GetMapping("modifyform") // 게시글 수정 페이지 GET
    public String modifyForm() { return "modifyform"; }
}