package com.example.demo.service;

import com.example.demo.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.vo.DataVO;

import java.util.List;
import java.util.Set;

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

    public DataVO findDataWithoutPassword(Integer page, Integer limit);

    boolean register(String userName, String password);

    public DataVO searchDataWithoutPassward(String userName, Integer page, Integer limit);

    public boolean deleteAccount(Integer accountId);

    Account findUserByUsername(String username);

    Set<String> findRolesByUsername(String username);

    Set<String> findPermissionsByUsername(String username);

    void updateAccountRoleById(Integer account_id,Integer role_id);

    Integer findIdByUsername(String username);
}
