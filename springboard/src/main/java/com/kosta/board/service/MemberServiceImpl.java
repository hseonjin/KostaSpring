package com.kosta.board.service;

import com.kosta.board.dao.MemberDAO;
import com.kosta.board.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDAO memberDAO;

    @Override
    public void join(Member mem) throws Exception {
        memberDAO.join(mem);
    }

    @Override
    public Member login(String id, String password) throws Exception {
        Member mem = memberDAO.login(id);
        if(mem==null) throw new Exception("아이디 오류");
        if(!mem.getPassword().equals(password)) throw new Exception("비밀번호 오류");
        return mem;
    }
}
