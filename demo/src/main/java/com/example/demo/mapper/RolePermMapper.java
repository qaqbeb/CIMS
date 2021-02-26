package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.RolePerms;
import org.apache.ibatis.annotations.Delete;

public interface RolePermMapper  extends BaseMapper<RolePerms> {

    @Delete("delete from role_perm rp "+
            "where rp.role_id=#{role_id}")
    void deleteByRoleId(Integer role_id);
}
