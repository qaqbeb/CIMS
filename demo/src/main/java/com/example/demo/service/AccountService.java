package com.example.demo.service;

import com.example.demo.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 7264
 * @since 2020-12-01
 */
public interface AccountService extends IService<Account> {
    int login(String username,String password);

    boolean register(String userName, String password, Integer power);
}
