package com.kosta.bank.dao;


import com.kosta.bank.dto.Member;
import org.mybatis.spring.SqlSessionTemplate;

public class MemberDAOImpl implements MemberDAO {
    private SqlSessionTemplate sqlSession;
    public SqlSessionTemplate sqlSession() {
        return sqlSession;
    }
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void join(Member mem) throws Exception {
        sqlSession.insert("mapper.member.join", mem);
    }

    @Override
    public Member login(String id) throws Exception {
        Member member = sqlSession.selectOne("mapper.member.login", id);
        return member;
    }
}
