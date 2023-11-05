package com.kosta.boardreview.service;


import com.kosta.boardreview.dto.Board;
import com.kosta.boardreview.dto.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.List;

public interface BoardService {
    // 게시글 리스트
    List<Board> boardListByPage(PageInfo pageInfo) throws Exception;

    // 게시글 작성(생성)
    Board boardWrite(Board board, MultipartFile file) throws Exception;

    // 파일 보기
    void fileView(Integer num, OutputStream out) throws Exception;

    // 게시글 상세
    Board boardDetail(Integer num) throws Exception;

    // 게시글 수정
    Board boardModify(Board board, MultipartFile file) throws Exception;

    // 게시글 삭제
    void boardDelete(Integer num) throws Exception;

    // 게시글 좋아요 체크 여부 (해당 게시글에 좋아요를 눌렀는지의 여부)
    Boolean isBoardLike(String userId, Integer boardNum) throws Exception;

    // 게시글 좋아요 선택/해제
    Boolean selectBoardLike(String userId, Integer boardNum) throws Exception;

}
