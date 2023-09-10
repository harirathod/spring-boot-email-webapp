package com.web.webapp.config;


import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration class containing configuration beans for the web layer.
 * Ensures the application has access to required resources.
 * No longer any need to extend a class (like WebSecurityConfigurerAdapter) for Spring Security.
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
     * @param httpSecurity Allows specification of how requests are secured using the builder pattern.
     * @return A SecurityFilterChain, which is a collection of servlet filters responsible for different aspects of security.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Define a set of filters for the HttpSecurity.
        httpSecurity
                /* authoriseHttpRequests returns an AuthorizedUrl, which defines the secured and unsecured URLs.
                 * The "/" URL should be unsecured. All other URLs should be secured.
                 */
                .authorizeHttpRequests((requests) ->
                        requests.requestMatchers("/").permitAll()
                                .anyRequest().authenticated())
                // The form login page, at "/login", should be accessible by all users.
                .formLogin((login) ->
                        login.loginPage("/login").permitAll())
                // All users should have access to the logout URL.
                .logout((logout) ->
                        logout.logoutUrl("/logout").permitAll());
        return httpSecurity.build();
    }

    /**
     * Configure static resources (JS, CSS) to be ignored by Spring Security.
     *
     * @return A WebSecurityCustomizer that customizes the WebSecurity.
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer()
    {
        return (web) -> web.ignoring().requestMatchers("/css/*", "/js/*", "/images/*");
    }


    /**
     * Defines a single authorised user.
     *
     * @param env The Environment that stores the values from the *.properties files. We use this Environment
     *            to retrieve the username and password, which are stored in database.properties.
     * @return A UserDetailsService which contains details of authorised users.
     */
    @Bean
    public UserDetailsService userDetailsService(Environment env, PasswordEncoder encoder)
    {
        UserDetails user = User.withUsername(env.getProperty("spring.datasource.username"))
                .password(encoder.encode(env.getProperty("spring.datasource.password")))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    /**
     * Defines a password encoder.
     *
     * @return A PasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}
