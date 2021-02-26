package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.service.RoleService;
import com.example.demo.vo.DataVO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/roleList")
    @ResponseBody
    public DataVO roleList(Integer page, Integer limit){
        return roleService.findRole(page, limit);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public boolean deleteRole(Integer id) {
        return roleService.deleteRole(id);
    }

    @RequestMapping("/add")
    @ResponseBody
    public boolean addRole(String name, String power, String codes, String intro){
        roleService.addRole(name, power, intro);
        Map map = (Map)JSON.parse(codes);
        roleService.addRoleRerm(name, map);
        return true;
    }

    @RequestMapping("/changePerms")
    @ResponseBody
    public boolean changeRoleRerm(String name, String codes){
        Map map = (Map)JSON.parse(codes);
        roleService.changeRoleRerm(name,map);
        return true;
    }

    @RequestMapping("/perms")
    @ResponseBody
    public List<String> permission(String data){
        String[] split = data.split("\"");
        List<String> permsByRoleName = roleService.findPermsByRoleName(split[5]);
        return permsByRoleName;
    }
}
