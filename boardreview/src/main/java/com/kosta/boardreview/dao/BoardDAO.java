package com.kosta.boardreview.dao;


import com.kosta.boardreview.dto.Board;
import com.kosta.boardreview.dto.FileVO;

import java.util.List;
import java.util.Map;

public interface BoardDAO {
    void insertBoard(Board board) throws Exception; // 게시글 생성
    List<Board> selectBoardList(Integer row) throws Exception; // 게시글 리스트
    Integer selectBoardCount() throws Exception; // 게시글 개수(페이징)
    Board selectBoard(Integer num) throws Exception; // 게시글 상세
    void updateBoard(Board board) throws Exception; // 게시글 수정
    void deleteBoard(Integer num) throws Exception; // 게시글 삭제
    List<Board> searchBoardList(Map<String, Object> param) throws Exception; // 검색 게시글 리스트
    Integer searchBoardCount(Map<String, Object> param) throws Exception; // 검색 게시글 개수(페이징)
    void updateBoardViewCount(Integer num) throws Exception; // 조회수
    Integer selectLikeCount(Integer num) throws Exception; // 좋아요수
    void plusBoardLikeCount(Integer num) throws Exception; // 좋아요수 +
    void minusBoardLikeCount(Integer num) throws Exception; // 좋아요수 -
    void insertFile(FileVO fileVO) throws Exception; // 파일 삽입
    FileVO selectFile(Integer num) throws Exception; // 파일 선택
    void deleteFile(Integer num) throws Exception; // 파일 삭제
    Integer selectBoardLike(Map<String, Object> param) throws Exception; // 좋아요 선택여부
    void insertBoardLike(Map<String, Object> param) throws Exception; // 좋아요 선택
    void deleteBoardLike(Map<String, Object> param) throws Exception; // 좋아요 삭제
}
