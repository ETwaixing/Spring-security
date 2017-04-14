package com.example.security;

import com.example.entity.AdminRole;
import com.example.entity.Administrator;
import com.example.service.AdministratorServcice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义实现服务UserDetailServices
 *
 * Created by yonglang on 2017/4/12.
 */
@Component
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private AdministratorServcice administratorServcice;

    /**
     * UserDetailsService  Springsecurity的 核心方法
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //账号信息
        Administrator administrator = administratorServcice.getAdminByUsername(s);
        //权限列表
        Set<GrantedAuthority> grantedAuths =getRoles(administrator);
        // 封装成spring security的user   需要根据权限格式进行改进
        User user = new User(administrator.getAdmin_name(), administrator.getAdmin_pass(),
                true, true,
                true, true,
                grantedAuths);
//        for (GrantedAuthority grantedAuthority:grantedAuths) {
//            System.out.println(grantedAuthority.getAuthority());
//        }
        return user;
    }
    //取得用户的权限
    private Set<GrantedAuthority> getRoles(Administrator administrato) {
        Set<GrantedAuthority> authSet = new HashSet<>();
        List<AdminRole> roles = administrato.getRoles();
        for(AdminRole role : roles) {
            GrantedAuthority grantedAuthority = ()->"ROLE_"+role.getRole_id();
            authSet.add(grantedAuthority);
        }
        return authSet;
    }
}
