package com.kosta.bank.dao;

import com.kosta.bank.dto.Account;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;
import java.util.Map;

public class AccountDAOImpl implements AccountDAO {
    private SqlSessionTemplate sqlSession;
    public SqlSessionTemplate sqlSession() {
        return sqlSession;
    }
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void insertAccount(Account acc) throws Exception {
        sqlSession.insert("mapper.account.insertAccount", acc);
    }

    @Override
    public Account selectAccount(String id) throws Exception {
        return sqlSession.selectOne("mapper.account.selectAccount", id);
    }

    @Override
    public void updateAccountBalance(Map<String, Object> param) throws Exception {
        sqlSession.update("mapper.account.updateBalance", param);
    }

    @Override
    public List<Account> selectAccountList() throws Exception {
        return sqlSession.selectList("mapper.account.selectAccountList");
    }

}
