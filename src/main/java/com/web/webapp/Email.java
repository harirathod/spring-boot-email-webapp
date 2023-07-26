package com.web.webapp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class defining an Email object.
 * Provides methods for accessing and modifying host, port, sender, recipient, content, subject, username, and password.
 * @version 2023.06.19
 * @author hari_rathod
 */
@Entity
public class Email
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private ZonedDateTime timestamp;

    // We have to use the full path name for @Email, as the containing class is also called Email.
    @NotBlank(message = "Cannot be blank.")
    @jakarta.validation.constraints.Email(message = "Must be an email.")
    private String sender;

    @NotBlank(message = "Cannot be blank.")
    @jakarta.validation.constraints.Email(message = "Must be an email.")
    private String recipient;

    @NotBlank(message = "Subject of the email cannot be empty.")
    @Length(max = 78)
    private String subject;

    @NotBlank(message = "Content of the email cannot be empty.")
    private String content;

    @NotBlank(message = "Host must be provided.")
    private String host;

    @NotBlank(message = "Port must be provided.")
    private String port;

    // Username and password should not be stored, hence they are transient.
    @Transient
    @NotBlank(message = "Username is required for validation.")
    private String username;

    @Transient
    @NotBlank(message = "Password is required for validation.")
    private String password;

    public String getSender()
    {
        return sender;
    }

    public String getRecipient()
    {
        return recipient;
    }

    public String getSubject()
    {
        return subject;
    }

    public String getContent()
    {
        return content;
    }

    public String getHost()
    {
        return host;
    }

    public String getPort()
    {
        return port;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getTimestamp()
    {
        ZonedDateTime timestampWithZone = timestamp.withZoneSameInstant(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE d MMMM, y, HH:mm:ss z");
        return timestampWithZone.format(formatter);
    }

    public Email setRecipient(String recipient)
    {
        this.recipient = recipient;
        return this;
    }

    public Email setSender(String sender)
    {
        this.sender = sender;
        return this;
    }

    public Email setSubject(String subject)
    {
        this.subject = subject;
        return this;
    }

    public Email setContent(String content)
    {
        this.content = content;
        return this;
    }

    public Email setHost(String host)
    {
        this.host = host;
        return this;
    }

    public Email setPort(String port)
    {
        this.port = port;
        return this;
    }

    public Email setPassword(String password)
    {
        this.password = password;
        return this;
    }

    public Email setUsername(String username)
    {
        this.username = username;
        return this;
    }

    /**
     * Before the entity is written / persisted, set the timestamp of the email.
     */
    @PrePersist
    public void setTimestamp()
    {
        timestamp = ZonedDateTime.now();
    }

    /**
     * Gets the string format of the email. This consists of the sender, the recipient, and the content of the email.
     * @return The string format of the email.
     */
    @Override
    public String toString()
    {
        return "From: " + sender + "\n"
                + "To: " + recipient + "\n"
                + "Content: " + content;
    }
}
