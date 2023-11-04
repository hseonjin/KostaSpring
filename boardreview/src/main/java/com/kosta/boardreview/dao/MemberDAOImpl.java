package com.kosta.boardreview.dao;

import com.kosta.boardreview.dto.Member;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO{
    @Autowired
    private SqlSessionTemplate sqlSession;

    // 회원가입
    @Override
    public void insertMember(Member member) throws Exception {
        sqlSession.insert("mapper.member.insertMember", member);
    }

    // 회원가입 중복확인 및 로그인
    @Override
    public Member selectMember(String id) throws Exception {
        return sqlSession.selectOne("mapper.member.selectMember", id);
    }
}
