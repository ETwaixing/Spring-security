package com.example.service;

import com.example.dao.AccoutDao;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yonglang on 2017/4/11.
 */
@Component
public class AccoutService {
    private final AccoutDao accoutDao;
    private final AccountRepository accountRepository;
    private final MongoTemplate template;
    @Autowired
    public AccoutService(AccoutDao accoutDao, AccountRepository accountRepository, MongoTemplate template) {
        this.accoutDao = accoutDao;
        this.accountRepository = accountRepository;
        this.template =template;
    }
    public List<Account> getAll(){
        return template.findAll(Account.class);
    }

}
