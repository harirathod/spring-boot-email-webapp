package com.web.webapp;

import jakarta.mail.MessagingException;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * Class that displays the email form and sends the email when the user accesses the webpage located at [domain]/sendEmail.
 * @version 2023.06.19
 * @author hari_rathod
 */
@Controller
public class EmailController
{
    // The repository for storing emails.
    private EmailRepository emailRepository;

    public EmailController(EmailRepository emailRepository)
    {
        this.emailRepository = emailRepository;
    }
    /**
     * Returns the HTML page 'send-email' when the url request for '/sendEmail' is made.
     * @param model The model.
     * @return The HTML page titled 'send-email'.
     */
    @GetMapping("/sendEmail")
    public String displayEmailForm(Model model)
    {
        model.addAttribute("email", new Email());
        return "send-email";
    }

    /**
     * When the form at /sendEmail is posted, this method receives it, and send the received email.
     * @param email The email to be sent.
     * @param errors Any errors associated with the email's validity.
     * @param model The model storing the email,
     * @return The HTML home page if the email was correctly send, otherwise, returns the 'send-email- homepage.
     * @throws MessagingException If there was an error in sending the email.
     */
    @PostMapping("/sendEmail")
    public String sendEmail(@Valid @ModelAttribute("email") Email email, BindingResult errors, Model model) throws MessagingException {
        // If there were any errors, do not send the email. Display the email form again.
        if (errors.hasErrors()) {
            return "send-Email";
        }
        try {
            EmailService.sendEmail(email);
            emailRepository.save(email);
        } catch (MessagingException e) {
            throw new MessagingException("There was an issue with sending your email. Were the parameters valid?", e);
        }
        return "redirect:/";
    }

    /**
     * Returns the list of all emails stored (i.e., the emails sent prior).
     * @return A list of all emails being stored.
     */
    @GetMapping("/data")
    @ResponseBody
    public List<Email> getAllEmails()
    {
        return emailRepository.findAll();
    }
}
