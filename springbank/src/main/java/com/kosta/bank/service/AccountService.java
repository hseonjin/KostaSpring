package com.kosta.bank.service;

import com.kosta.bank.dto.Account;

public interface AccountService {
    void makeAccount(Account acc) throws Exception;
    Account accountInfo(String id) throws Exception;
}
