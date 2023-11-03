package com.kosta.board.service;

import com.kosta.board.dto.Board;
import com.kosta.board.dto.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.List;

public interface BoardService {
    List<Board> boardListByPage(PageInfo pageInfo) throws Exception; // 글 리스트 (+페이징처리)
    Board writeBoard(Board board, MultipartFile file) throws Exception; // 글 작성
    void fileView(Integer num, OutputStream out) throws Exception; // file 보기
    Board boardDetail(Integer num) throws Exception; // 글 상세
    Board modifyBoard(Board board, MultipartFile file) throws Exception; // 글 수정
    void removeBoard(Integer num) throws Exception; // 글 삭제

    Boolean isBoardLike(String userId, Integer boardNum) throws Exception; // 사용자가 글을 선택했는지 여부 가져오기
    Boolean selectBoardLike(String userId, Integer boardNum) throws Exception; // 사용자가 선택한 경우 처리하고 선택여부 가져오기
}
