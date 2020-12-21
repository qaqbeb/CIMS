package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Account;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 7264
 * @since 2020-12-01
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Autowired
    private AccountMapper mapper;

    @Override
    public int login(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("account_name", username);
        map.put("password", password);
        List<Account> accountList = new ArrayList<>();
        accountList = mapper.selectByMap(map);
        if (mapper.selectByMap(map).size() != 0) {
            return accountList.get(0).getPower();
        } else {
            return 0;
        }
    }

    @Override
    public boolean register(String userName, String password, Integer power) {
        Account account = new Account();
        account.setAccountName(userName);
        account.setPassword(password);
        account.setPower(power);
        int i = mapper.insert(account);
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }
}
