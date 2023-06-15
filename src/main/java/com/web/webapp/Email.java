package com.web.webapp;


import javax.validation.constraints.NotNull;

public class Email
{
    @NotNull(message = "Sender is required.")
    private String from;

    @NotNull(message = "Recipients are required.")
    private String to;

    private String content;

    public String toString() {
        return "From: " + from + "\n"
                + "To: " + to + "\n"
                + "Content: " + content;
    }
}
