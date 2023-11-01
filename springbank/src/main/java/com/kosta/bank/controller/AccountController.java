package com.kosta.bank.controller;

import com.kosta.bank.dto.Account;
import com.kosta.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;
    // main 페이지를 열어주는 메소드
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String main() {
        return "main";
    }
    // 계좌개설 페이지를 get 방식으로 불러오는 메소드
    @RequestMapping(value="makeAccount", method = RequestMethod.GET)
    public String makeAccount() {
        return "makeAccount";
    }
    // makeAccount Post
    @RequestMapping(value="makeAccount", method = RequestMethod.POST)
    public String makeAccount(@ModelAttribute Account acc, Model model) {
        try {
            accountService.makeAccount(acc);
            Account sacc = accountService.accountInfo(acc.getId());
            model.addAttribute("acc", acc);
            return "accountInfo";
        } catch (Exception e) {
            e.printStackTrace();
//            model.addAttribute("err", "계좌개설 실패");
            return "error";
        }
    }
    // 입금
    @RequestMapping(value="deposit", method = RequestMethod.GET)
    public String deposit() {
        return "deposit";
    }
    // 출금
    @RequestMapping(value="withdraw", method = RequestMethod.GET)
    public String withdraw() {
        return "withdraw";
    }
    // 출금
    @RequestMapping(value="accountInfo", method = RequestMethod.GET)
    public String accountInfo() {
        return "accountInfoForm";
    }
    // 출금
    @RequestMapping(value="allAccountInfo", method = RequestMethod.GET)
    public String allAccountInfo() {
        return "allAccountInfo";
    }
}
