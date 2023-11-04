package com.kosta.boardreview.dao;


import com.kosta.boardreview.dto.Board;
import com.kosta.boardreview.dto.FileVO;

import java.util.List;

public interface BoardDAO {
    // 게시글 작성
    void insertBoard(Board board) throws Exception;

    // 게시글 개수
    Integer selectBoardCount() throws Exception;

    // 게시글 리스트
    List<Board> selectBoardList(Integer row) throws Exception;

    // 게시글 상세
    Board selectBoard(Integer num) throws Exception;

    // 파일 삽입
    void insertFile(FileVO fileVO) throws Exception;

    // 파일 선택
    FileVO selectFile(Integer num) throws Exception;
}
