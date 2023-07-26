package com.web.webapp;

import com.web.webapp.config.WebConfig;
import com.web.webapp.repository.EmailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.time.ZonedDateTime;

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

    private Email email1;
    private Email email2;
    private Email email3;

    @BeforeEach
    public void setUp()
    {
        email1 = new Email().setUsername("my_username")
                .setPassword("secret_password")
                .setContent("content1")
                .setRecipient("r1@r.com")
                .setHost("smtp.unknown1.com")
                .setPort("001")
                .setSender("s1@s.com")
                .setSubject("subject1");

        email2 = new Email().setUsername("my_username")
                .setPassword("secret_password")
                .setContent("content2")
                .setRecipient("r2@r.com")
                .setHost("smtp.unknown2.com")
                .setPort("002")
                .setSender("s2@s.com")
                .setSubject("subject2");

        email3 = new Email().setUsername("my_username")
                .setPassword("secret_password")
                .setContent("content3")
                .setRecipient("r3@r.com")
                .setHost("smtp.unknown3.com")
                .setPort("003")
                .setSender("s3@s.com")
                .setSubject("subject3");
    }

    /**
     * Test that the password and username are not saved to the database (checking security, essentially).
     */
    @Test
    public void testPrivacyOfCredentials() {
        emailRepository.save(email1);
        // Need to flush and clear.
        testEntityManager.flush();
        testEntityManager.clear();
        Email retrievedEmail = emailRepository.findAll().get(0);

        assertNotNull(retrievedEmail);
        assertNull(retrievedEmail.getPassword());
        assertNull(retrievedEmail.getUsername());
    }

    /**
     * Test that emails are written to the database with the correct timestamp.
     */
    @Test
    public void testTimestamp()
    {
        ZonedDateTime z1 = ZonedDateTime.now();
        emailRepository.save(email1);
        ZonedDateTime z3 = ZonedDateTime.now();

        testEntityManager.flush();
        testEntityManager.clear();
        Email retrievedEmail = emailRepository.findAll().get(0);
        ZonedDateTime z2 = retrievedEmail.getUnformattedTimestamp();
        assertTrue(z1.compareTo(z2) < 0);
        assertTrue(z2.compareTo(z3) < 0);
    }

    /**
     * Test that emails are correctly saved to the database.
     */
    @Test
    public void testSave()
    {
        assertSame(0, emailRepository.findAll().size());

        emailRepository.save(email1);
        testEntityManager.flush();
        testEntityManager.clear();

        assertSame(1, emailRepository.findAll().size());

        Email retrievedEmail = emailRepository.findAll().get(0);
        assertEquals("s1@s.com", retrievedEmail.getSender());
        assertEquals("r1@r.com", retrievedEmail.getRecipient());
        assertEquals("content1", retrievedEmail.getContent());
        assertEquals("subject1", retrievedEmail.getSubject());
        assertEquals("001", retrievedEmail.getPort());
        assertEquals("smtp.unknown1.com", retrievedEmail.getHost());
    }

    /**
     * Test that emails are retrieved correctly with the custom findAllSortedByTimestamp() method.
     */
    @Test
    public void testFindAllSortedByTimestamp()
    {

    }
}
