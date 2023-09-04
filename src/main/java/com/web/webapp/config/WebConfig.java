package com.web.webapp.config;


import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Configuration class containing configuration beans. Ensures the application has access to required resources.
 * @version 2023.06.19
 * @author hari_rathod
 */
@Configuration
@PropertySource("classpath:database.properties")
@EnableWebSecurity
public class WebConfig {
    /**
     * This method identifies the application.properties file, allowing the messages inside the
     * properties file to be accessed by Thymeleaf. Spring Boot can automatically detect the application.properties
     * file, but only if there is one '*.properties' file. As there are two '*.properties' files, we need
     * this method to explicitly configure the MessageSource.
     *
     * @return The MessageSource associated with the 'application.properties' file.
     */
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("application");
        return messageSource;
    }

    /**
     * Configure which paths do and do not require authentication.
     *
     * @return A SecurityFilterChain which contains the HttpSecurity configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        return null;
    }
}
