package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Account;
import com.example.demo.entity.AccountRole;
import com.example.demo.entity.Goods;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.mapper.AccountRoleMapper;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.vo.AccountVO;
import com.example.demo.vo.DataVO;
import com.example.demo.vo.GoodsVO;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    private AccountRoleMapper acMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int login(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("account_name", username);
        map.put("password", password);
        List<Account> accountList = new ArrayList<>();
        accountList = mapper.selectByMap(map);
        return 1;
    }

    @Override
    public DataVO findDataWithoutPassword(Integer page, Integer limit) {
        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");

        // 分页查询
        IPage<Account> accountIPage = new Page<>(page, limit);
        IPage<Account> reuslt = mapper.selectPage(accountIPage, null);

        dataVO.setCount(reuslt.getTotal());
        List<Account> accountList = reuslt.getRecords();
        // List<Goods> goodsList = goodsMapper.selectList(null);
        List<AccountVO> accountVOList = new ArrayList<>();
        for (Account account : accountList) {
            AccountVO accountVO = new AccountVO();
            accountVO.setAccountId(account.getAccountId());
            accountVO.setAccountName(account.getAccountName());
            List<Integer> roleByAccount = acMapper.findRoleByAccount(account.getAccountId());
            if(roleByAccount.size()>0){
                Integer role_id = roleByAccount.get(0);
                List<String> roleNameById = roleMapper.findRoleNameById(role_id);
                accountVO.setPower(roleNameById.get(0));
            }
            accountVOList.add(accountVO);
        }
        dataVO.setData(accountVOList);
        return dataVO;
    }

    @Override
    public boolean register(String userName, String password) {
        Account account = new Account();
        account.setAccountName(userName);
        String salt = RandomStringUtils.randomNumeric(6);
        account.setSalt(salt);
        Md5Hash md5Hash = new Md5Hash(password, salt);
        account.setPassword(md5Hash.toString());
        mapper.insert(account);
        return true;
    }

    @Override
    public DataVO searchDataWithoutPassward(String userName, Integer page, Integer limit) {
        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("account_name", userName);
        IPage<Account> accountIPage = new Page<>(page, limit);
        IPage<Account> result = mapper.selectPage(accountIPage, queryWrapper);
        List<Account> accountList = result.getRecords();
        dataVO.setCount(result.getTotal());
        List<AccountVO> accountVOList = new ArrayList<>();
        for (Account account : accountList) {
            AccountVO accountVO = new AccountVO();
            BeanUtils.copyProperties(account, accountVO);
            accountVOList.add(accountVO);
        }
        dataVO.setData(accountVOList);
        return dataVO;
    }

    @Override
    public boolean deleteAccount(Integer accountId) {
        Set<Integer> byId = mapper.findAccountRoleById(accountId);
        Iterator<Integer> iterator = byId.iterator();
        while(iterator.hasNext()){
            Integer next = iterator.next();
            acMapper.deleteById(next);
        }
        mapper.deleteById(accountId);
        return true;
    }

    @Override
    public Account findUserByUsername(String username) {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("account_name",username);
        List<Account> accounts = mapper.selectList(wrapper);
        if(accounts != null&& accounts.size()>0){
            return accounts.get(0);
        }
        return null;
    }

    @Override
    public Set<String> findRolesByUsername(String username) {
        Set<String> rolesByUsername = mapper.findRolesByUsername(username);
        return rolesByUsername;
    }

    @Override
    public Set<String> findPermissionsByUsername(String username) {
        Set<String> permissionsByUsername = mapper.findPermissionsByUsername(username);
        return permissionsByUsername;
    }

    @Override
    public void updateAccountRoleById(Integer account_id, Integer role_id) {
        List<Integer> role = acMapper.findRoleByAccount(account_id);
        if(role.size()>0)
            mapper.updateAccountRoleById(account_id,role_id);
        else{
            AccountRole accountRole = new AccountRole();
            accountRole.setAccount_id(account_id);
            accountRole.setRole_id(role_id);
            acMapper.insert(accountRole);
        }
    }

    @Override
    public Integer findIdByUsername(String username) {
        return mapper.findIdByUsername(username);
    }
}
