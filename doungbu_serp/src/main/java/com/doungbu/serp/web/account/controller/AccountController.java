package com.doungbu.serp.web.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }
    
    @PostMapping("/login")
    public String cjk() {
    	//accountDao.getUser("test1");
    	return "redirect:/board/list";
    }
    
    @GetMapping("/register")
    public String register() {
        return "account/register";
    }
    
    @PostMapping("/register")
    public String register(String te) {
    	
        return "redirect:/account/login";
    }
}
