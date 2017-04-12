package com.example.service;

import com.example.dao.AdministratorDao;
import com.example.entity.AdminRole;
import com.example.entity.Administrator;
import com.example.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 自定义用户验证权限
 *
 * Created by yonglang on 2017/4/11.
 */
@Component
public class AdministratorServcice implements UserDetailsService{
    private final AdministratorDao administratorDao;
    private final AdministratorRepository administratorRepository;
    private final MongoTemplate template;
    @Autowired
    public AdministratorServcice(AdministratorDao administratorDao, AdministratorRepository administratorRepository, MongoTemplate template) {
        this.administratorDao = administratorDao;
        this.administratorRepository = administratorRepository;
        this.template = template;
    }

    /**
     *   获得所有后台用户
     */
    public List<Administrator> getAll(){
        return template.findAll(Administrator.class);
    }
    /**
     *   通过账号获得单个用户
     */
    public Administrator getAdminByUsername(String username){
        Criteria criteria = new Criteria("admin_name").is(username);
        return template.findOne(Query.query(criteria),Administrator.class);
    }
    /**
     * TODO: UserDetailsService  Springsecurity的 核心方法
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //账号信息
        Administrator administrator = getAdminByUsername(s);
        //权限列表
        Set<GrantedAuthority> grantedAuths =getRoles(administrator);
        //String[] auth = {"USER"};
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
