package com.web.webapp;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Class for dealing with exceptions. For example, if a @Controller throws a MessagingException, this class deals with that exception
 * in an appropriate manner (i.e., by displaying a HTML error page).
 * @version 2023.06.22
 * @author hari_rathod
 */
@ControllerAdvice
public class GlobalExceptionHandler
{
    /**
     * Returns an 'error' page when a MessagingException is thrown.
     * @param exception The MessagingException object thrown by another class, elsewhere in the program.
     * @param request The http request made when this exception was thrown.
     * @return The ModelAndView object representing the HTML 'error' page.
     */
    @ExceptionHandler(MessagingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleMessagingException(HttpServletRequest request, MessagingException exception)
    {
        // Use the HTML page 'error'.
        ModelAndView modelAndView = new ModelAndView("error");

        // Add the exception and Http request to the model, for more details.
        modelAndView.addObject("exception", exception.getMessage());
        modelAndView.addObject("url", request.getRequestURL().toString());
        modelAndView.addObject("httpstatus", 400);
        modelAndView.addObject("img", "/warning.png");

        return modelAndView;
    }
}
