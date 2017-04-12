package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by yonglang on 2017/4/11.
 */
public interface AccountRepository extends MongoRepository<Account, String>{
}
