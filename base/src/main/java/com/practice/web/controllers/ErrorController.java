package com.practice.web.controllers;

import com.practice.dto.Error;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
public class ErrorController extends AbstractErrorController {

    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping("/error")
    public Error handleError(HttpServletRequest request) {
        Exception e = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        return new Error(500 ,e.getCause().getMessage());
    }
}
