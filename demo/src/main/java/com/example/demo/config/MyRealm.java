package com.example.demo.config;

import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Set;

import javax.servlet.http.HttpSession;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private AccountService accountService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        // Account Account = AccountService.AccountAuthority(primaryPrincipal);
        // String role = Account.getDuty();
        // System.out.println(role);
        // List<Authority> authorities = Account.getAuthorityList();
        // List<String> list = new ArrayList<>();
        // 获取session
        // ServletRequestAttributes attrs = (ServletRequestAttributes)
        // RequestContextHolder.getRequestAttributes();
        // HttpSession session = attrs.getRequest().getSession();
        // session.setAttribute("authorities",authorities);
        // session.setAttribute("role", role);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Account primaryPrincipal = (Account) principalCollection.getPrimaryPrincipal();
        String name = primaryPrincipal.getAccountName();
        Set<String> roles = accountService.findRolesByUsername(name);
        if (roles != null && roles.size() > 0) {
            simpleAuthorizationInfo.addRoles(roles);
        }
        Set<String> perms = accountService.findPermissionsByUsername(name);
        if (perms != null && perms.size() > 0) {
            simpleAuthorizationInfo.addStringPermissions(perms);
        }
        
        // ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // HttpSession session = attrs.getRequest().getSession();
        // session.setAttribute("perms", perms);
        // session.setAttribute("roles", roles);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = ((UsernamePasswordToken) token).getUsername();
        Account account = accountService.findUserByUsername(username);
        if (account == null) {
            return null;
        } else {
            String password = account.getPassword();
            ByteSource byteSource = new SimpleByteSource(account.getSalt());
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(account, password,
                    byteSource, getName());
            return simpleAuthenticationInfo;

        }
    }
}
