package com.kosta.bank.dao;


import com.kosta.bank.dto.Member;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {
    @Autowired
    private SqlSessionTemplate sqlSession;

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
