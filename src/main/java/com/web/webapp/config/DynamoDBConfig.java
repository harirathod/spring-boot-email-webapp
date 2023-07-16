package com.web.webapp.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreamsClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Class for configuring the database (in this case, AWS).
 * @author Hari Rathod
 * @version 2023.07.16
 */
@Configuration
@PropertySource("classpath:database.properties")
@EnableDynamoDBRepositories(basePackages = "com.web.webapp")
public class DynamoDBConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.dynamodb.region")
    private String amazonDynamoDBRegion;

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    /**
     * Configure the AmazonDynamoDB client.
     * @return The AmazonDynamoDB client (interface with the database).
     */
    @Bean
    public AmazonDynamoDB amazonDynamoDB()
    {
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials()))
                .withEndpointConfiguration(new EndpointConfiguration(amazonDynamoDBEndpoint, amazonDynamoDBRegion))
                .build();

        return amazonDynamoDB;
    }

    /**
     * Gets the AWS credentials as an AWSCredentials object.
     * @return The AWS credentials.
     */
    @Bean
    public AWSCredentials awsCredentials()
    {
        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    }
}


