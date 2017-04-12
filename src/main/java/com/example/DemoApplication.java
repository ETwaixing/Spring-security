package com.example;

import com.example.config.SpringDataMongodbConfig;
import com.example.utils.MongoUtil;
import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.UnknownHostException;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	@Autowired
	private SpringDataMongodbConfig springDataMongodbConfig;
//	@Autowired
//    private MongoTemplate mongoTemplate;
//    @Autowired
//    private MongoClient mongoClient;
//    @Autowired
//    private MongoClientURI mongoClientURI;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	//配置数据库源
	@Bean(name = "mongoClientURI")
	@Primary
	public MongoClientURI mongoClientURI() throws UnknownHostException {
		MongoClientOptions.Builder builder = MongoClientOptions.builder().connectionsPerHost(10).writeConcern(WriteConcern.ACKNOWLEDGED);
		return new MongoClientURI(springDataMongodbConfig.getUri(), builder);
	}
	@Bean(name = "mongoClient")
	@Primary
	public MongoClient mongoClient(@Autowired MongoClientURI clientURI) throws UnknownHostException {
		return new MongoClient(clientURI);
	}
	@SuppressWarnings("deprecation")
	@Bean(name="template")
	@Primary
	public MongoTemplate mongoTemplate(@Autowired MongoClientURI clientURI) throws UnknownHostException{
		MongoURI mongoURI = new MongoURI(clientURI);
		Mongo mongo = new Mongo(mongoURI);
		return new MongoTemplate(mongo, mongoURI.getDatabase());
	}
	//测试mongo对象注入问题
	@Override
	public void run(String... strings) throws Exception {
//		System.out.println(MongoUtil.getDB()!=null);
//		System.out.println(MongoUtil.getDB());
//        System.out.println(mongoTemplate!=null);
//        System.out.println(mongoClient!=null);
//        System.out.println(mongoClientURI!=null);
	}
}
