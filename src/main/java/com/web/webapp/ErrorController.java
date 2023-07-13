package com.web.webapp;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Class defining when error pages should be displayed.
 * @version 2023.07.14
 * @author Hari Rathod
 */
@Controller
public class ErrorController {

    /**
     * Display the error.html page when a mapping to /error is made.
     * @return The error.html page.
     */
    @GetMapping("/error")
    public String handleError(HttpServletResponse response)
    {
        // TODO: (Potentially - time permitting) add separate error pages for 404, 500, etc.
        // The error code should not be set here yet. This is a generic error page.
        return "error";
    }

}
