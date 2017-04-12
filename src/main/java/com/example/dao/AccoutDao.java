package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by yonglang on 2017/4/11.
 */
@Component
public class AccoutDao {
    @Autowired
    private MongoTemplate mongoTemplate;

}
