package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.AccountRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AccountRoleMapper  extends BaseMapper<AccountRole> {
    @Select("select distinct ar.role_id "+
            "from account_role ar "+
            "where ar.account_id=#{account_id}")
    List<Integer> findRoleByAccount(Integer account_id);
}
