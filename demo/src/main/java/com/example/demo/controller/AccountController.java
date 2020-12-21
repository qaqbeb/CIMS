package com.example.demo.controller;

import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 7264
 * @since 2020-12-01
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        // 接收参数
        String userName = request.getParameter("username");
        String userPassword = request.getParameter("password");
        int loginSuccess = accountService.login(userName, userPassword);
        if (loginSuccess == 1) {
            HttpSession session = request.getSession();
            session.setAttribute("username", userName);
            session.setAttribute("password", userPassword);
            response.sendRedirect("/index1.html"); // 登录成功重定向到主页
        } else if (loginSuccess == 2) {
            HttpSession session = request.getSession();
            session.setAttribute("username", userName);
            session.setAttribute("password", userPassword);
            response.sendRedirect("/index2.html"); // 登录成功重定向到主页
        } else if (loginSuccess == 3) {
            HttpSession session = request.getSession();
            session.setAttribute("username", userName);
            session.setAttribute("password", userPassword);
            response.sendRedirect("/index3.html"); // 登录成功重定向到主页
        } else { // 登录失败，重定向登录页面
            response.sendRedirect("/signin.html");
        }
    }

    @RequestMapping(value = "/register")
    @ResponseBody
    public boolean registerNewUser(String userName, String password, Integer power) {
        boolean registerSuccess = accountService.register(userName, password, power);
        return registerSuccess;
    }
}
