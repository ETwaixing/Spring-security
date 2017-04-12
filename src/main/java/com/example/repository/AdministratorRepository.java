package com.example.repository;

import com.example.entity.Administrator;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by yonglang on 2017/4/11.
 */
public interface AdministratorRepository extends MongoRepository<Administrator, String>{
}
