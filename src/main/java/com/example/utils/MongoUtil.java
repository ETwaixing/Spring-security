package com.example.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by yonglang on 2017/4/12.
 */
@Component
public class MongoUtil {
    private static MongoClientURI mongoClientURI;
    private static MongoClient mongoClient;
    @Autowired
    public MongoUtil(MongoClientURI mongoClientURI, MongoClient mongoClient) {
        this.mongoClientURI = mongoClientURI;
        this.mongoClient = mongoClient;
    }

    /**
     * 创建MongoDatabase对象的工具方法
     *
     * @return
     */
    public static MongoDatabase getDB() {
        return mongoClient.getDatabase(mongoClientURI.getDatabase());
    }

}
