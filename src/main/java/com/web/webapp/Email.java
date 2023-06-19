package com.web.webapp;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * Class defining an Email object.
 * Provides methods for accessing and modifying host, port, sender, recipient, content, subject, username, and password.
 * @version 2023.06.19
 * @author hari_rathod
 */
public class Email
{
    // We have to use the full path name for @Email, as the containing class is also called Email.
    @NotBlank(message = "Cannot be blank.")
    @jakarta.validation.constraints.Email(message = "Must be an email.")
    private String from;

    @NotBlank(message = "Cannot be blank.")
    @jakarta.validation.constraints.Email(message = "Must be an email.")
    private String to;

    @NotBlank(message = "Subject of the email cannot be empty.")
    @Length(max = 78)
    private String subject;

    @NotBlank(message = "Content of the email cannot be empty.")
    private String content;

    @NotBlank(message = "Host must be provided.")
    private String host;

    @NotBlank(message = "Port must be provided.")
    private String port;

    @NotBlank(message = "Username is required for validation.")
    private String username;

    @NotBlank(message = "Password is required for validation.")
    private String password;

    public String getFrom()
    {
        return from;
    }

    public String getTo()
    {
        return to;
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

    public void setTo(String to)
    {
        this.to = to;
    }

    public void setFrom(String from)
    {
        this.from = from;
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

    public String toString()
    {
        return "From: " + from + "\n"
                + "To: " + to + "\n"
                + "Content: " + content;
    }
}
