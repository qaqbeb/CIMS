package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Role;
import com.example.demo.entity.RolePerms;
import com.example.demo.mapper.PermissionMapper;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.mapper.RolePermMapper;
import com.example.demo.service.RoleService;
import com.example.demo.vo.DataVO;
import com.example.demo.vo.RoleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermMapper rpMapper;

    @Autowired
    private PermissionMapper pMapper;

    @Override
    public List<String> findPermsByRoleName(String name) {
        return roleMapper.findPermsByRoleName(name);
    }

    @Override
    public DataVO findRole(Integer page, Integer limit) {
        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");

        // 分页查询
        IPage<Role> roleIPage = new Page<>(page, limit);
        IPage<Role> reuslt = roleMapper.selectPage(roleIPage, null);

        dataVO.setCount(reuslt.getTotal());
        List<Role> roleList = reuslt.getRecords();
        // List<Goods> goodsList = goodsMapper.selectList(null);
        List<RoleVO> roleVOList = new ArrayList<>();
        for (Role role : roleList) {
            RoleVO roleVO = new RoleVO();
            BeanUtils.copyProperties(role, roleVO);
            roleVOList.add(roleVO);
        }
        dataVO.setData(roleVOList);
        return dataVO;
    }

    @Override
    public boolean deleteRole(Integer id) {

        rpMapper.deleteByRoleId(id);
        /*
         * Set<Integer> byId = roleMapper.findRolePermsById(id);
         * 
         * Iterator<Integer> iterator = byId.iterator(); while(iterator.hasNext()){
         * Integer next = iterator.next(); rpMapper.deleteById(next); }
         */

        roleMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean addRole(String name, String power, String intro) {
        Role role = new Role();
        // RolePerms rolePerms = new RolePerms();
        role.setCode(power);
        role.setName(name);
        role.setIntro(intro);
        int i = roleMapper.insert(role);
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addRoleRerm(String name, Map<String, String> codes) {
        List<Integer> role_id = roleMapper.findIdByRoleName(name);
        Set<String> strings = codes.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (codes.get(next).equals("on")) {
                List<Integer> perm_id = pMapper.findIdByName(next);
                RolePerms rolePerms = new RolePerms();
                rolePerms.setRole_id(role_id.get(0));
                rolePerms.setPerm_id(perm_id.get(0));
                rpMapper.insert(rolePerms);

            }
        }
        return true;
    }

    @Override
    public boolean changeRoleRerm(String name, Map<String, String> codes) {
        List<Integer> role_id = roleMapper.findIdByRoleName(name);
        // System.out.println(">>>>>>>>>>>>>>>"+role_id.get(0));
        rpMapper.deleteByRoleId(role_id.get(0));
        Set<String> strings = codes.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (codes.get(next).equals("on")) {
                List<Integer> perm_id = pMapper.findIdByName(next);
                RolePerms rolePerms = new RolePerms();
                rolePerms.setRole_id(role_id.get(0));
                rolePerms.setPerm_id(perm_id.get(0));
                rpMapper.insert(rolePerms);
            }
        }
        return true;
    }

    @Override
    public List<Integer> findIdByRoleName(String name) {
        return roleMapper.findIdByRoleName(name);
    }

}
