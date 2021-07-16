package com.java.aws.kstudy.kafka.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
    basePackages = {"com.java.aws.kstudy.kafka.domain"},
    mongoTemplateRef = "secondaryMongoTemplate"
)
public class SecondarMongoDBRepositoryConfig {

}
