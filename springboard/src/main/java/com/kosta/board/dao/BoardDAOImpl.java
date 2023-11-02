package com.kosta.board.dao;

import com.kosta.board.dto.Board;
import com.kosta.board.dto.FileVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BoardDAOImpl implements BoardDAO {
    @Autowired
    private SqlSessionTemplate sqlSession; // null을 가지고 있음 -> @Autowired 어노테이션을 통해 주입하여 사용됨

    @Override
    public void insertBoard(Board board) throws Exception {
        sqlSession.insert("mapper.board.insertBoard");
    }

    @Override
    public List<Board> selectBoardList(Integer row) throws Exception {
        return sqlSession.selectList("mapper.board.selectBoardList", row);
    }

    @Override
    public Integer selectBoardCount() throws Exception {
        return sqlSession.selectOne("mapper.board.selectBoardCount");
    }

    @Override
    public Board selectBoard(Integer num) throws Exception {
        return sqlSession.selectOne("mapper.board.selectBoard", num);
    }

    @Override
    public void updateBoard(Board board) throws Exception {
        sqlSession.update("mapper.board.updateBoard", board);
    }

    @Override
    public void deleteBoard(Integer num) throws Exception {
        sqlSession.delete("mapper.board.deleteBoard", num);
    }

    @Override
    public List<Board> searchBoardList(Map<String, Object> param) throws Exception {
        return sqlSession.selectOne("mapper.board.searchBoardList", param);
    }

    @Override
    public Integer searchBoardCount(Map<String, Object> param) throws Exception {
        return sqlSession.selectOne("mapper.board..searchBoardCount", param);
    }

    @Override
    public void updateBoardViewCount(Integer num) throws Exception {
        sqlSession.update("mapper.board.updateBoardViewCount", num);
    }

    @Override
    public Integer selectLikeCount(Integer num) throws Exception {
        return sqlSession.selectOne("mapper.board.selectLikeCount", num);
    }

    @Override
    public void plusBoardLikeCount(Integer num) throws Exception {
        sqlSession.update("mapper.board.plusBoardLikeCount", num);
    }

    @Override
    public void minusBoardLikeCount(Integer num) throws Exception {
        sqlSession.update("mapper.board.minusBoardLikeCount", num);
    }

    @Override
    public void insertFile(FileVO fileVO) throws Exception {
        sqlSession.insert("mapper.board.insertFile", fileVO);
    }

    @Override
    public FileVO selectFile(Integer num) throws Exception {
        return sqlSession.selectOne("mapper.board.selectFile", num);
    }
}
