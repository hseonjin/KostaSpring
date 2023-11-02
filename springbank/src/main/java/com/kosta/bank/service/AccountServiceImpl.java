package com.kosta.bank.service;

import com.kosta.bank.dao.AccountDAO;
import com.kosta.bank.dao.AccountDAOImpl;
import com.kosta.bank.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
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
        Account acc = accountDAO.selectAccount(id);
        if (acc==null) throw new Exception("계좌번호 오류");
        return accountDAO.selectAccount(id);
    }

    @Override
    public void deposit(String id, Integer balance) throws Exception {
        Account acc = accountInfo(id);
        acc.deposit(balance);
        Map<String, Object> param = new HashMap<>();
        param.put("id", acc.getId());
        param.put("balance", acc.getBalance());
        accountDAO.updateAccountBalance(param);
    }
    @Override
    public void withdraw(String id, Integer balance) throws Exception {
        Account acc = accountInfo(id);
        acc.withdraw(balance);
        Map<String, Object> param = new HashMap<>();
        param.put("id", acc.getId());
        param.put("balance", acc.getBalance());
        accountDAO.updateAccountBalance(param);
    }

    @Override
    public List<Account> allAccountInfo() throws Exception {
        return accountDAO.selectAccountList();
    }
}