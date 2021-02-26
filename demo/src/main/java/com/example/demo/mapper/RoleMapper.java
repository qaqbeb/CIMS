package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

public interface RoleMapper extends BaseMapper<Role> {
    @Select("select distinct p.name "+
            "from role r,role_perm rp,permission p "+
            "where rp.role_id = r.id and rp.perm_id = p.id and r.name=#{name}")
    List<String> findPermsByRoleName(String name);

    @Select("select distinct r.id "+
            "from role r "+
            "where r.name=#{name}")
    List<Integer> findIdByRoleName(String name);

    @Select("select distinct r.name "+
            "from role r "+
            "where r.id=#{id}")
    List<String> findRoleNameById(Integer id);

    @Select("select distinct rp.id "+
            "from role r,role_perm rp"+
            "where rp.role_id = r.id and r.id=#{id}")
    Set<Integer> findRolePermsById(Integer id);
}
