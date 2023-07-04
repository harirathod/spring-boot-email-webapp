package com.web.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

/**
 * The main Spring Boot class.
 */
@RestController
@SpringBootApplication
public class WebApplication
{
	/**
	 * The entry point into the application.
	 * @param args Optional arguments. As of now, they do nothing.
	 */
	public static void main(String[] args)
	{
		SpringApplication.run(WebApplication.class, args);
	}
}
