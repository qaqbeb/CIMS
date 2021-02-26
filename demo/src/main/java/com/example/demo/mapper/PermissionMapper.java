package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {
    @Select("select distinct p.id "+
            "from permission p "+
            "where p.name=#{name}")
    List<Integer> findIdByName(String name);
}
