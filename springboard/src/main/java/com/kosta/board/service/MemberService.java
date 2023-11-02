package com.kosta.board.service;

import com.kosta.board.dto.Member;

public interface MemberService {
    void join(Member mem) throws Exception;
    Member login(String id, String password) throws Exception;
}
