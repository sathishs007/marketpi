package com.java.aws.kstudy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
    basePackages = {"com.java.aws.kstudy.domain"},
    mongoTemplateRef = "primaryMongoTemplate"
)
public class PrimaryMongoDBRepositoryConfig {

}
