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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = EmailController.class)
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
}
