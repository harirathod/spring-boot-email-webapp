package com.web.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:database.properties")

public class DynamoDBConfig {

}
