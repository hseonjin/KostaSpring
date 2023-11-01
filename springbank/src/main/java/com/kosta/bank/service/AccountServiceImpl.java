package com.kosta.bank.service;

import com.kosta.bank.dao.AccountDAO;
import com.kosta.bank.dao.AccountDAOImpl;
import com.kosta.bank.dto.Account;

public class AccountServiceImpl implements AccountService {

    private AccountDAO accountDAO;
    public void setAccountDAO(AccountDAOImpl accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void makeAccount(Account acc) throws Exception {
        accountDAO.insertAccount(acc);
    }

    @Override
    public Account accountInfo(String id) throws Exception {
        return accountDAO.selectAccount(id);
    }
}