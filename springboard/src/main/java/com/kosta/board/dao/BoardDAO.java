package com.kosta.board.dao;

import com.kosta.board.dto.Board;
import com.kosta.board.dto.FileVO;

import java.util.List;
import java.util.Map;

public interface BoardDAO {
    void insertBoard(Board board) throws Exception;
    List<Board> selectBoardList(Integer row) throws Exception;
    Integer selectBoardCount() throws Exception;
    Board selectBoard(Integer num) throws Exception;
    void updateBoard(Board board) throws Exception;
    void deleteBoard(Integer num) throws Exception;
    List<Board> searchBoardList(Map<String, Object> param) throws Exception;
    Integer searchBoardCount(Map<String, Object> param) throws Exception;
    void updateBoardViewCount(Integer num) throws Exception;
    Integer selectLikeCount(Integer num) throws Exception;
    void plusBoardLikeCount(Integer num) throws Exception;
    void minusBoardLikeCount(Integer num) throws Exception;
    void insertFile(FileVO fileVO) throws Exception;
    FileVO selectFile(Integer num) throws Exception;
    void deleteFile(Integer num) throws Exception;

    // 좋아요 (board like)
    Integer selectBoardLike(Map<String, Object> param) throws Exception;
    void insertBoardLike(Map<String, Object> param) throws Exception;
    void deleteBoardLike(Map<String, Object> param) throws Exception;
}
