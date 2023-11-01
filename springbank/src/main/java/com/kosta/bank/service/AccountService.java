package com.kosta.bank.service;

import com.kosta.bank.dto.Account;

import java.util.List;

public interface AccountService {
    void makeAccount(Account acc) throws Exception;
    Account accountInfo(String id) throws Exception;
    void deposit(String id, Integer balance) throws Exception;
    void withdraw(String id, Integer balance) throws Exception;
    List<Account> allAccountInfo() throws Exception;
}
