package com.web.webapp.config;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Configuration class containing configuration beans. Ensures the application has access to required resources.
 * @version 2023.06.19
 * @author hari_rathod
 */
@Configuration
@PropertySource("classpath:database.properties")
public class WebConfig
{
    /**
     * This Configuration Bean identifies the properties file, allowed the messages inside the
     * properties file to be accessed by Thymeleaf.
     * @return The Bean that identifies the application.properties file.
     */
    @Bean
    public ResourceBundleMessageSource messageSource()
    {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("application");
        return messageSource;
    }
}
