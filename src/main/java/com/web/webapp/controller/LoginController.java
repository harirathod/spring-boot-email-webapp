package com.web.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Class defining what happens at the "/login" mapping.
 *
 * @author hari_rathod
 * @version 2023.09.04
 */
@Controller
public class LoginController {

    /**
     * Display "login.html" when a GET request is made to "/login".
     * @return A String representing the contents of the "login.html" file.
     */
    @GetMapping("/login")
    public String displayLoginPage()
    {
        return "login";
    }
}
