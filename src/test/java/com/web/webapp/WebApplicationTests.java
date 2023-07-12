package com.web.webapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WebApplicationTests {

	@Autowired
	private EmailController emailController;

	@Test
	public void contextLoads() {
		assertNotNull(emailController);
	}
}
