package com.web.webapp;

import com.web.webapp.config.WebConfig;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({ WebConfig.class, EmailController.class })
public class DataRetrievalTests
{
    @Autowired
    private EmailController emailController;

    @Test
    public void testPrivacyOfCredentials() throws MessagingException {

    }
}
