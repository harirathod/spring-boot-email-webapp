package com.web.webapp;


import javax.validation.constraints.NotNull;

public class Email
{
    @NotNull(message = "Sender is required.")
    private String from;

    @NotNull(message = "Recipients are required.")
    private String to;

    private String content;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getContent() {
        return content;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return "From: " + from + "\n"
                + "To: " + to + "\n"
                + "Content: " + content;
    }
}
