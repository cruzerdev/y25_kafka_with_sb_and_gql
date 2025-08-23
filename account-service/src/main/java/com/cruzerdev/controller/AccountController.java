package com.cruzerdev.controller;


import com.cruzerdev.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acc")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam String accountId,@RequestParam double amount){
        return  accountService.withdraw(accountId,amount);
    }


}
