package com.example.service;

import com.example.dao.AdministratorDao;
import com.example.entity.Administrator;
import com.example.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 *
 * Created by yonglang on 2017/4/11.
 */
@Component
public class AdministratorServcice{
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


}
