package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;
import com.example.demo.service.RoleService;
import com.example.demo.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/userList")
    @ResponseBody
    public DataVO findDataWithoutPassword(Integer page, Integer limit) {
        return accountService.findDataWithoutPassword(page, limit);
    }

    @RequestMapping(value = "/searchUser")
    @ResponseBody
    public DataVO searchDataWithoutPassward(String userName, Integer page, Integer limit) {
        return accountService.searchDataWithoutPassward(userName, page, limit);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public boolean deleteUser(Integer accountId) {
        return accountService.deleteAccount(accountId);
    }

    @RequestMapping(value = "/changeRole")
    @ResponseBody
    public boolean changeRole(String name,String code) {
        System.out.println(name);
        System.out.println(code);
        Integer account_id = accountService.findIdByUsername(name);
        List<Integer> role_id = roleService.findIdByRoleName(code);
        accountService.updateAccountRoleById(account_id, role_id.get(0));
        return true;
    }



}
