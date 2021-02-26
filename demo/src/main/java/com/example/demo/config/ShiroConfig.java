package com.example.demo.config;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setUnauthorizedUrl("/withoutCode.html");
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        Map<String, String> map = new LinkedHashMap<>();

        map.put("/api/**", "anon");
        map.put("/css/**", "anon");
        map.put("/images/**", "anon");
        map.put("/js/**", "anon");
        map.put("/lib/**", "anon");
        map.put("/register1", "anon");

        map.put("/toLogin", "anon");
        map.put("/login", "anon");
        map.put("/register", "anon");
        // map.put("/**","authc");

        map.put("/home", "authc");
        map.put("/home.html", "authc");
        map.put("/logout", "logout");

        map.put("/addGoods.html", "perms[goods:addGoods]");
        map.put("/searchGoods.html", "perms[goods:searchGoods]");
        map.put("/customerList.html", "perms[customer:customerList]");
        map.put("/addCustomer.html", "perms[customer:addCustomer]");
        map.put("/searchCustomer.html", "perms[customer:searchCustomer]");
        map.put("/placeOrder.html", "perms[order:placeOrder]");
        map.put("/checkOrder.html", "perms[order:checkOrder]");
        map.put("/orderList.html", "perms[order:orderList]");
        map.put("/warehouseList.html", "perms[warehouse:warehouseList]");
        map.put("/stock.html", "perms[warehouse:stock]");
        map.put("/transfer.html", "perms[warehouse:transfer]");
        map.put("/goodsList.html", "perms[goods:goodsList]");
        map.put("/userList.html", "perms[user:userList]");
        map.put("/searchUser.html", "perms[user:searchUser]");
        map.put("/roleList.html", "perms[role:roleList]");
        map.put("/warehouseStatistic.html", "perms[warehouse:statistic]");
        // map.put("/addRole.html","perms[role:addRole]");
        // map.put("/turnover.html","perms[goods:goodsList]");

        /*
         * map.put("/goods/goodsList","perms[goods:goodsList]");
         * map.put("/goods/addGoods","perms[goods:addGoods]");
         * map.put("/goods/searchGoods","perms[goods:searchGoods]");
         * map.put("/customer/customerList","perms[customer:customerList]");
         * map.put("/customer/addCustomer","perms[customer:addCustomer]");
         * map.put("/customer/searchCustomer","perms[customer:searchCustomer]");
         * map.put("/order/placeOrder","perms[/order/placeOrder]");
         * map.put("/order/orderList","perms[order:orderList]");
         * map.put("/warehouse/warehouseList","perms[warehouse:warehouseList]");
         * map.put("/order/checkOrder","perms[order:checkOrder]");
         * map.put("/warehouse/stock","perms[warehouse:stock]");
         * map.put("/warehouse/transfer","perms[warehouse:transfer]");
         * map.put("/warehouse/goodsList","perms[warehouse:goodsList]");
         * map.put("/user/userList","perms[user:userList]");
         * map.put("/user/searchUser","perms[user:searchUser]");
         */

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());
        return securityManager;
    }

    @Bean
    public Realm myRealm() {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(credentialsMatcher());
        return myRealm;
    }

    @Bean
    public CredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        return matcher;
    }
}
