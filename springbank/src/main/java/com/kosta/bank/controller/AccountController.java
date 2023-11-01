package com.kosta.bank.controller;

import com.kosta.bank.dto.Account;
import com.kosta.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String main() {
        return "main";
    }

    @RequestMapping(value="makeAccount", method = RequestMethod.GET)
    public String makeAccount() {
        return "makeAccount";
    }

    @RequestMapping(value="makeAccount", method = RequestMethod.POST)
    public String makeAccount(@ModelAttribute Account sacc, Model model) {
        try {
            accountService.makeAccount(sacc);
            Account acc = accountService.accountInfo(sacc.getId());
            model.addAttribute("acc", acc);
            return "accountInfo";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("err", "계좌개설 실패");
            return "error";
        }
    }

    @RequestMapping(value="deposit", method = RequestMethod.GET)
    public String deposit() {
        return "deposit";
    }

    @RequestMapping(value="deposit", method = RequestMethod.POST)
    public String deposit(@RequestParam("id") String id, @RequestParam("balance") Integer balance, Model model) {
        try {
            accountService.deposit(id, balance);
            Account acc = accountService.accountInfo(id);
            model.addAttribute("acc", acc);
            return "accountInfo";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping(value="withdraw", method = RequestMethod.GET)
    public String withdraw() {
        return "withdraw";
    }

    @RequestMapping(value="withdraw", method = RequestMethod.POST)
    public String withdraw(@RequestParam("id") String id, @RequestParam("balance") Integer balance, Model model) {
        try {
            accountService.withdraw(id, balance);
            Account acc = accountService.accountInfo(id);
            model.addAttribute("acc", acc);
            return "accountInfo";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping(value="accountInfo", method = RequestMethod.GET)
    public String accountInfo() {
        return "accountInfoForm";
    }

    @RequestMapping(value="accountInfo", method = RequestMethod.POST)
    public String accountInfo(@RequestParam("id") String id, Model model) {
        try {
            Account acc = accountService.accountInfo(id);
            model.addAttribute("acc", acc);
            return "accountInfo";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("err", "계좌오류");
            return "error";
        }
    }

    @RequestMapping(value="allAccountInfo", method = RequestMethod.GET)
    public String allAccountInfo(Model model) {

        try {
            List<Account> accs = accountService.allAccountInfo();
            model.addAttribute("accs", accs);
            return "allAccountInfo";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("err", "전체계좌조회 실패");
            return "error";
        }
    }
}
