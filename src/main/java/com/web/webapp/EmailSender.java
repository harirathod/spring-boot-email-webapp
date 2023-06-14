package com.web.webapp;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

/**
 * Class for sending emails between users.
 */
public class EmailSender
{

    /**
     * Gets an email template, belonging to the provided credentials of [username, password].
     * @param username The username of the sender.
     * @param password The password of the sender.
     * @param host The mail server / host.
     * @return The message for this session.
     */
    private static Message getMessageForSession(String username, String password, String host)
    {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        return new MimeMessage(session);
    }

    /**
     * Adds the sender and recipients to the message.
     * @param message The message we want to add senders to.
     * @param fromEmail The sender's email address.
     * @param toEmail The comma separated list of recipients' email addresses. Overwrites any existing recipients' addresses.
     * @return The message, with sender and recipients added.
     * @throws RuntimeException If there was an error in adding the sender and recipients to the message.
     */
    private static Message prepareMessageSenders(Message message, String fromEmail, String toEmail)
    {
        try {
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return message;
    }

    /**
     * Adds the subject and message content to the email.
     * @param message The message we want to add text to.
     * @param subject The subject of the message.
     * @param content The content (body) of the message. Overwrites any existing content in the message.
     * @throws RuntimeException If there was an error in adding content to the message.
     */
    private static Message addMessageContent(Message message, String subject, String content)
    {
        try {
            message.setSubject(subject);

            // Create a body part with the text.
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setText(content);

            // Create a multipart as a container for the body part, and add the multipart to the message.
            Multipart multipart = new MimeMultipart(mimeBodyPart);
            message.setContent(multipart);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return message;
    }

    /**
     * Sends the message.
     * @param message The message to send.
     * @throws MessagingException If there was a problem with sending the message.
     */
    private static void sendMessage(Message message) throws MessagingException {
        Transport.send(message);
    }
}
