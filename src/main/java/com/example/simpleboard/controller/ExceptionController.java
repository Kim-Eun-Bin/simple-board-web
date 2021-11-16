package com.example.simpleboard.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.example.simpleboard.controller")
public class ExceptionController {
    @ExceptionHandler(IllegalArgumentException.class)
    public String notFound(Exception e, Model model) {
        model.addAttribute("exception", e);

        return "errors/404-error";
    }
}
