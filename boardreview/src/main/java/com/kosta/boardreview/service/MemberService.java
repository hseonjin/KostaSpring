package com.kosta.boardreview.service;

import com.kosta.boardreview.dto.Member;

public interface MemberService {
    // 회원가입
    void join(Member member) throws Exception;

    // 회원가입 중복확인
    Member userInfo(String id) throws Exception;

    // 로그인
    Member login(String id, String password) throws Exception;
}
