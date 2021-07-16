package com.java.aws.kstudy.kafka.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
@Configuration
public class MultipleMongoConfig {

	 @Primary
	    @Bean(name = {"primaryMongoProperties"})
	    @ConfigurationProperties(prefix = "spring.data.mongodb")
	public MongoProperties getPrimary() {
		return new MongoProperties();
	}
	 
	    @Bean(name = {"secondaryMongoProperties"})
	    @ConfigurationProperties(prefix = "mongodb")
	public MongoProperties getsecondary() {
		return new MongoProperties();
	}
	 @Primary
	    @Bean(name = {"primaryMongoTemplate"})
public MongoTemplate  primaryMongoTemplate() {
	return new MongoTemplate(primaryMongoDatabaseFactory(getPrimary()));
}
	 
	    @Bean(name = {"secondaryMongoTemplate"})
public MongoTemplate  secondaryMongoTemplate() {
	return new MongoTemplate(secondaryMongoDatabaseFactory(getsecondary()));
	
	
}
	 
	 @Primary
	    @Bean
	    public MongoDatabaseFactory primaryMongoDatabaseFactory(MongoProperties mongo )  {
	        return new SimpleMongoClientDatabaseFactory(mongo.getUri());
	    }
	 
	    @Bean
	    public MongoDatabaseFactory secondaryMongoDatabaseFactory(MongoProperties mongo )  {
	        return new SimpleMongoClientDatabaseFactory(mongo.getUri());
	    }
	
}
