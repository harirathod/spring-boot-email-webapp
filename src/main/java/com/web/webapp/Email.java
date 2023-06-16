package com.web.webapp;

import jakarta.validation.constraints.NotBlank;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Email
{
    // We have to use the full path name for @Email, as the containing class is also called Email.
    @NotBlank(message = "Cannot be blank.")
    @jakarta.validation.constraints.Email(message = "Must be an email.")
    private String from;

    @NotBlank(message = "Cannot be blank.")
    @jakarta.validation.constraints.Email(message = "Must be an email.")
    private String to;

    @NotBlank(message = "Content of the email cannot be empty.")
    private String content;

    public String getFrom()
    {
        return from;
    }

    public String getTo()
    {
        return to;
    }

    public String getContent()
    {
        return content;
    }

    public void setTo(String to)
    {
        this.to = to;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String toString()
    {
        return "From: " + from + "\n"
                + "To: " + to + "\n"
                + "Content: " + content;
    }
}
