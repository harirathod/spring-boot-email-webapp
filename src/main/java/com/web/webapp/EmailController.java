package com.web.webapp;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DateFormat;
import java.util.Date;


@Controller
public class EmailController
{
    @GetMapping("/sendEmail")
    public String displayEmailForm(Model model)
    {
        model.addAttribute("email", new Email());
        return "send-email";
    }

    @PostMapping("/sendEmail")
    public String sendEmail(@Valid @ModelAttribute("email") Email email, BindingResult errors, Model model)
    {
        // If there were any errors, do not send the email. Display the email form again.
        if (errors.hasErrors()) {
            return "send-Email";
        }
        // TODO: Otherwise if no errors, EmailService.sendEmail(email).
        return "redirect:/";
    }
}
