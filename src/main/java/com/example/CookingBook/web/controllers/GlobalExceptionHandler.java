package com.example.CookingBook.web.controllers;

import com.example.CookingBook.services.ProductNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler extends BaseController{

    @ExceptionHandler({ProductNotFoundException.class})
    public ModelAndView handleSqlException(ProductNotFoundException e){
        ModelAndView modelAndView = new ModelAndView("error");


        modelAndView.addObject("message" , e.getMessage());

        return modelAndView;
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ModelAndView handleUserException(UsernameNotFoundException e){
        ModelAndView modelAndView = new ModelAndView("error");


        modelAndView.addObject("message" , e.getMessage());

        return modelAndView;
    }
}
