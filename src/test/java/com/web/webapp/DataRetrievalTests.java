package com.web.webapp;

import com.web.webapp.config.WebConfig;
import com.web.webapp.repository.EmailRepository;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({ WebConfig.class })
public class DataRetrievalTests
{
    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    /**
     * Test that the password and username are not saved to the database (checking security, essentially).
     */
    @Test
    public void testPrivacyOfCredentials() {
        Email email = new Email();
        email.setUsername("my_username")
                .setPassword("secret_password")
                .setContent("content")
                .setRecipient("r@r.com")
                .setHost("smtp.unknown.com")
                .setPort("000")
                .setSender("s@s.com")
                .setSubject("subject");

        emailRepository.save(email);
        // Need to flush and clear.
        testEntityManager.flush();
        testEntityManager.clear();
        Email retrievedEmail = emailRepository.findAll().get(0);

        assertNotNull(retrievedEmail);
        assertNull(retrievedEmail.getPassword());
        assertNull(retrievedEmail.getUsername());
    }
}
