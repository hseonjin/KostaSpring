package com.kosta.board.dao;

import com.kosta.board.dto.Member;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public void join(Member mem) throws Exception {
        sqlSession.insert("mapper.member.insertMember", mem);
    }

    @Override
    public Member login(String id) throws Exception {
        return sqlSession.selectOne("mapper.member.selectMember", id);
    }
}
