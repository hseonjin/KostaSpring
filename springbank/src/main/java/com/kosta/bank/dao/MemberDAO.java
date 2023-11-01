package com.kosta.bank.dao;

import com.kosta.bank.dto.Member;

public interface MemberDAO {
    void join(Member mem) throws Exception;
    Member login(String id) throws Exception;
}
