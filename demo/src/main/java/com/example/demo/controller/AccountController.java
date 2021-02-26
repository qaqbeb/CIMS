package com.example.demo.controller;

import com.example.demo.service.AccountService;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 7264
 * @since 2020-12-01
 */
@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/toLogin")
    public String loginUser(){
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){
        try{
            Subject subject = SecurityUtils.getSubject();
            AuthenticationToken token = new UsernamePasswordToken(username,password);
            subject.login(token);
            return "redirect:/home";
        }catch(Exception e){
            return "redirect:/toLogin";
        }
    }


    @GetMapping(value = "/home")
    public String home(){
        return "home";
    }

  /*  @GetMapping(value = "/index2")
    public String Index2(){
        return "index2";
    }

    @GetMapping(value = "/index3")
    public String Index3(){
        return "index3";
    }*/

    @GetMapping("/logout")
    public String Logout(){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "redirect:/signin";
    }

    @GetMapping("/register")
    public String Register(){
        return "signup";
    }

    @RequestMapping(value = "/register1")
    @ResponseBody
    public boolean RegisterNewUser(String userName, String password){
        boolean registerSuccess = accountService.register(userName, password);
        return registerSuccess;
    }
}
