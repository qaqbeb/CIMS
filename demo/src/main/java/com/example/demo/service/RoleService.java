package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Role;
import com.example.demo.vo.DataVO;

import java.util.List;
import java.util.Map;

public interface RoleService extends IService<Role> {
    public List<String> findPermsByRoleName(String name);

    public DataVO findRole(Integer page, Integer limit);

    public boolean deleteRole(Integer id);

    public boolean addRole(String name, String power, String intro);

    public boolean addRoleRerm(String name,Map<String,String> codes);

    public boolean changeRoleRerm(String name,Map<String,String> codes);

    public List<Integer> findIdByRoleName(String name);
}
