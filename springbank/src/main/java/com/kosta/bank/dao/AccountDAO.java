package com.kosta.bank.dao;

import com.kosta.bank.dto.Account;

import java.util.List;
import java.util.Map;

public interface AccountDAO {
    void insertAccount(Account acc) throws Exception;
    Account selectAccount(String id) throws Exception;
    void updateAccountBalance(Map<String, Object> param) throws Exception;
    List<Account> selectAccountList() throws Exception;
}
