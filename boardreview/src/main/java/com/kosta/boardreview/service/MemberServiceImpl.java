package com.kosta.boardreview.service;

import com.kosta.boardreview.dao.MemberDAO;
import com.kosta.boardreview.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDAO memberDAO;

    @Override
    public void join(Member member) throws Exception {
        Member smem = memberDAO.selectMember(member.getId());
        if(smem!=null) throw new Exception("이미 존재하는 회원입니다"); // 회원가입하려는 id가 이미 존재하는 경우(중복)
        memberDAO.insertMember(member);
    }

    // 회원가입 중복확인
    @Override
    public Member userInfo(String id) throws Exception {
        return memberDAO.selectMember(id);
    }

    // 로그인
    @Override
    public Member login(String id, String password) throws Exception {
        Member member = memberDAO.selectMember(id);
        if(member==null) throw new Exception("아이디가 틀렸습니다"); // 해당 id로 등록된 회원정보가 없는 경우
        if(!member.getPassword().equals(password))
            throw new Exception("비밀번호가 틀렸습니다"); // 입력한 pw와 db의 pw 비교하여 일치하지 않는 경우
        return member;
    }
}
