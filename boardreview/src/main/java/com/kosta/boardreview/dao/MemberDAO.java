package com.kosta.boardreview.dao;

import com.kosta.boardreview.dto.Member;

public interface MemberDAO {
    // 회원가입
    void insertMember(Member member) throws Exception;

    // 회원가입 중복확인 및 로그인
    Member selectMember(String id) throws Exception;
}
