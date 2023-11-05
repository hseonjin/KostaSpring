package com.kosta.boardreview.controller;

import com.kosta.boardreview.dto.Board;
import com.kosta.boardreview.dto.Member;
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
import java.util.Map;
import java.util.logging.Logger;

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
            Member user = (Member) session.getAttribute("user"); // 좋아요 기능 위해 사용
            if(user!=null) {
                Boolean select = service.isBoardLike(user.getId(), num);
                mav.addObject("select", select);
            }
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

    @PostMapping("like") // 좋아요 기능 POST (ajax 통신)
    @ResponseBody // return 해주는 것이 view가 아닌 data
    public String boardLike(@RequestParam("num") Integer num) {
        Member user = (Member) session.getAttribute("user");
        try {
            if(user==null) throw new Exception("로그인이 필요한 기능입니다");
            Boolean select = service.selectBoardLike(user.getId(), num);
            return String.valueOf(select);
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    // 검색
    @PostMapping("boardsearch") // 검색한 게시글 리스트 POST
    public ModelAndView boardModify(@RequestParam("type") String type,
                                    @RequestParam("keyword") String keyword,
                                    @RequestParam(value="page", required=false, defaultValue = "1") Integer page) {
        ModelAndView mav = new ModelAndView();
        try {
            if(type.equals("all")) { mav.setViewName("boardlist"); }
            Map<String, Object> res = service.searchListByPage(type, keyword, page);
            mav.addObject("boardList", res.get("boardList"));
            // res 객체를 그대로 줬을 때 jsp에서 boardList를 인식하지 못하는 이슈가 발생
            // res 객체에서 boardList 키값을 찾아 넘겨주도록 설정
            mav.setViewName("boardlist");

        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("err", "검색 오류");
            mav.setViewName("error");
        }
        return mav;
    }
}