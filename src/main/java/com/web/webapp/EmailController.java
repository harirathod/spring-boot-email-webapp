package com.web.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailController {

    @GetMapping("/sendEmail")
    public String displayEmailForm(Model model) {
        model.addAttribute("email", new Email());
        return "send-email";
    }

    @PostMapping("/sendEmail")
    public String sendEmail(Model model, @ModelAttribute("email") Email email) {
        return "redirect:/";
    }
}
