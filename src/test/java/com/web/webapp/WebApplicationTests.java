package com.web.webapp;

import com.web.webapp.controller.EmailController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class WebApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EmailController emailController;

	@Test
	public void contextLoads() {
		assertNotNull(emailController);
	}
}
