package com.kosta.boardreview.dao;

import com.kosta.boardreview.dto.Board;
import com.kosta.boardreview.dto.FileVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDAOImpl implements BoardDAO {
    @Autowired
    private SqlSessionTemplate sqlSession;

    // 게시글 작성
    @Override
    public void insertBoard(Board board) throws Exception {
        sqlSession.insert("mapper.board.insertBoard", board);
    }

    // 게시글 개수
    @Override
    public Integer selectBoardCount() throws Exception {
        return sqlSession.selectOne("mapper.board.selectBoardCount");
    }

    // 게시글 리스트
    @Override
    public List<Board> selectBoardList(Integer row) throws Exception {
        return sqlSession.selectList("mapper.board.selectBoardList", row);
    }

    // 게시글 상세
    @Override
    public Board selectBoard(Integer num) throws Exception {
        return sqlSession.selectOne("mapper.board.selectBoard", num);
    }

    // 파일 삽입
    @Override
    public void insertFile(FileVO fileVO) throws Exception {
        sqlSession.insert("mapper.board.insertFile", fileVO);
    }

    // 파일 선택
    @Override
    public FileVO selectFile(Integer num) throws Exception {
        return sqlSession.selectOne("mapper.board.selectFile", num);
    }
}
