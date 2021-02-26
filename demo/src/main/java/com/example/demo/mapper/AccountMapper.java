package com.example.demo.mapper;

import com.example.demo.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 7264
 * @since 2020-12-01
 */
public interface AccountMapper extends BaseMapper<Account> {

    @Select("select distinct r.code "+
            "from role r, account u,account_role ur "+
            "where r.id = ur.role_id and u.account_id = ur.account_id and u.account_name=#{username}")
    Set<String> findRolesByUsername(String username);

    @Select("select distinct p.code "+
            "from role r, account u,account_role ur,role_perm rp,permission p "+
            "where r.id = ur.role_id and u.account_id = ur.account_id and rp.role_id = r.id and rp.perm_id = p.id and u.account_name=#{username}")
    Set<String> findPermissionsByUsername(String username);

    @Select("select distinct ur.id "+
            "from account u,account_role ur "+
            "where u.account_id = ur.account_id and u.account_id=#{id}")
    Set<Integer> findAccountRoleById(Integer id);

    @Delete("update account_role ur "+
            "set ur.role_id=#{role_id} "+
            "where ur.account_id=#{account_id}")
    void updateAccountRoleById(Integer account_id,Integer role_id);

    @Select("select distinct u.account_id "+
            "from account u "+
            "where u.account_name=#{username}")
    Integer findIdByUsername(String username);
}
