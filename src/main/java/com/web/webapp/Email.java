package com.web.webapp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

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

    public long getId() {
        return id;
    }

    public void setRecipient(String recipient)
    {
        this.recipient = recipient;
    }

    public void setSender(String sender)
    {
        this.sender = sender;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public void setPort(String port)
    {
        this.port = port;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String toString()
    {
        return "From: " + sender + "\n"
                + "To: " + recipient + "\n"
                + "Content: " + content;
    }
}
