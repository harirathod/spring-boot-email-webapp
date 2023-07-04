package com.web.webapp;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

/**
 * Class for sending emails between users. If the email service requires two-factor authentication, the user
 * should use an 'app password' (like <a href="https://myaccount.google.com/apppasswords">Google app password</a> for Gmail, or a Microsoft app password for Outlook).
 */
public class EmailService
{
    /**
     * Sends an email. Combines getMessageForSession, prepareMessageSenders, addMessageContent, and
     * sendMessage into one method.
     * @param email The email object containing a list of properties. The properties that should be defined in the email are are [host, port, from, to, subject, content, username, password].
     * @throws MessagingException If there was a problem sending the email.
     */
    public static void sendEmail(Email email) throws MessagingException
    {
        Message m = EmailService.getMessageForSession(email.getHost(), email.getPort());
        EmailService.prepareMessageSenders(m, email.getSender(), email.getRecipient());
        EmailService.addMessageContent(m, email.getSubject(), email.getContent());
        EmailService.sendMessage(m, email.getUsername(), email.getPassword());
    }

    /**
     * Gets an email template, belonging to the provided credentials of [username, password].
     * @param host The mail server / host.
     * @return The message for this session.
     */
    private static Message getMessageForSession(String host, String port)
    {
        Properties properties = new Properties();
        // Add some default properties. We do not use SSL by default.
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties);
        return new MimeMessage(session);
    }

    /**
     * Adds the sender and recipients to the message.
     * @param message The message we want to add senders to.
     * @param fromEmail The sender's email address.
     * @param toEmail The comma separated list of recipients' email addresses. Overwrites any existing recipients' addresses.
     * @throws MessagingException If there was an error in adding the sender and recipients to the message.
     */
    private static void prepareMessageSenders(Message message, String fromEmail, String toEmail) throws MessagingException
    {
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
    }

    /**
     * Adds the subject and message content to the email.
     * @param message The message we want to add text to.
     * @param subject The subject of the message.
     * @param content The content (body) of the message. Overwrites any existing content in the message.
     * @throws MessagingException If there was an error in adding content to the message.
     */
    private static void addMessageContent(Message message, String subject, String content) throws MessagingException
    {
        message.setSubject(subject);
        // Create a body part with the text.
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(content, "text/html; charset=utf-8");

        // Create a multipart as a container for the body part, and add the multipart to the message.
        Multipart multipart = new MimeMultipart(mimeBodyPart);
        message.setContent(multipart);
    }

    /**
     * Sends the message.
     * @param message The message to send.
     * @param username The username of the sender.
     * @param password The password of the sender.
     * @throws MessagingException If there was a problem with sending the message.
     */
    private static void sendMessage(Message message, String username, String password) throws MessagingException
    {
        Transport.send(message, username, password);
    }
}
