package com.web.webapp;

import com.web.webapp.config.WebConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Class for testing web mappings, such as /sendEmail, are handled correctly.
 * @version 2023.07.14
 * @author Hari Rathod
 */
@WebMvcTest(controllers = { EmailController.class, ErrorController.class })
@TestPropertySource(locations = "classpath:application.properties")
@Import(WebConfig.class)
public class WebLayerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailRepository emailRepository;

    /**
     * Test that mapping /sendEmail works, in the EmailController class. It should return a form in an HTML page "send-email.html".
     */
    @Test
    public void testSendEmailMapping() throws Exception
    {
        mockMvc.perform(get("/sendEmail"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome to this Email Service!")))
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("send-email"));
    }

    /**
     * Test the POST mapping of the email form in send-email.html.
     */
    @Test
    public void testSendEmailPostMapping() throws Exception
    {
        // Check that the email is not submitted when the user doesn't fill out all the parameters, so the send-email page is displayed again.
        mockMvc.perform(post("/sendEmail"))
                .andExpect(view().name("send-email"));

        mockMvc.perform(post("/sendEmail")
                .param("sender", "unknown@u.com")
                .param("recipient", "unknown@u.com")
                .param("host", "unknown")
                .param("port", "unknown")
                .param("username", "unknown")
                .param("password", "unknown")
                .param("subject", "unknown")
                .param("content", "unknown"))
                .andExpect(view().name("error"))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test the error page is correctly displayed on invalid mappings.
     */
    @Test
    public void testErrorMapping() throws Exception
    {

        mockMvc.perform(get("/error"))
                .andExpect(content().string(containsString("Page not found.")))
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("error"));
    }
}
