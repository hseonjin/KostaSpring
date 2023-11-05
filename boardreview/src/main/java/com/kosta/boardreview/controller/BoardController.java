package com.kosta.boardreview.controller;

import com.kosta.boardreview.dto.Board;
import com.kosta.boardreview.dto.PageInfo;
import com.kosta.boardreview.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/") // 기본 루트
public class BoardController {
    @Autowired
    private BoardService service;
    @Autowired
    HttpSession session;

    @GetMapping("/") // 메인 페이지 GET
    public String main(){
        return "main";
    } // view 이름을 작성하는 것

    @GetMapping("boardwrite") // 게시글 작성 페이지 GET
    public String boardWrite() { return "writeform"; }

    @PostMapping("boardwrite") // 게시글 작성 페이지 POST
    public ModelAndView boardWrite(@ModelAttribute Board board, @RequestParam("file") MultipartFile file) {
        ModelAndView mav = new ModelAndView();
        try {
            Board boardwrite = service.boardWrite(board, file);
            mav.addObject("board", boardwrite);
            mav.setViewName("detailform");
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("err","글 등록 오류");
            mav.setViewName("error");
        }
        return mav;
    }

    @GetMapping("boardlist") // 게시글 리스트 페이지 GET
    public ModelAndView boardList(@RequestParam(value="page", required=false, defaultValue = "1") Integer page) {
        ModelAndView mav = new ModelAndView();
        try {
            PageInfo pageInfo = new PageInfo();
            pageInfo.setCurPage(page);
            List<Board> boardList = service.boardListByPage(pageInfo);
            mav.addObject("pageInfo", pageInfo);
            mav.addObject("boardList", boardList);
            mav.setViewName("boardlist");
        } catch(Exception e) {
            e.printStackTrace();
            mav.setViewName("error");
        }
        return mav;
    }

    @RequestMapping("image/{num}")
    public void imageView(@PathVariable Integer num, HttpServletResponse response) {
        try {
            service.fileView(num, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("boarddetail/{num}") // 게시글 상세 페이지 GET
    public ModelAndView detailForm(@PathVariable Integer num) {
        ModelAndView mav = new ModelAndView();
        try {
            Board board = service.boardDetail(num);
            mav.addObject("board", board);
            mav.setViewName("detailform");
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("err", "글 상세 조회 실패");
            mav.setViewName("error");
        }
        return mav;
    }

    @GetMapping("boardmodify/{num}") // 게시글 수정 페이지 GET
    public ModelAndView boardModify(@PathVariable Integer num) {
        ModelAndView mav = new ModelAndView();
        try {
            Board board = service.boardDetail(num); // 선택한 게시글의 정보 찾아 저장
            mav.addObject("board", board);
            mav.setViewName("modifyform");
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("err", "수정할 게시글 정보 조회 오류");
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping("boardmodify") // 게시글 수정 페이지 POST
    public ModelAndView boardModify(@ModelAttribute Board board, @RequestParam("file") MultipartFile file) {
        ModelAndView mav = new ModelAndView();
        try {
            Board modify = service.boardModify(board, file);
            mav.addObject("board", modify);
            mav.setViewName("detailform"); // 수정 완료 후 디테일페이지 이동
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("err","글 수정 오류");
            mav.setViewName("error");
        }
        return mav;
    }

    @GetMapping("boarddelete/{num}/{page}") // 게시글 삭제 GET - 페이지 조정까지 해주어야 함
    public String boardDelete(@PathVariable Integer num, @PathVariable Integer page) {
        try {
            service.boardDelete(num);
            return "redirect:/boardlist?page=" + page;
        } catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}