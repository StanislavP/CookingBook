package com.example.CookingBook.web.controllers;

import com.example.CookingBook.web.annotations.PageTitle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController{

    @GetMapping("/")
    @PageTitle("Home")
    public ModelAndView index() {
        return super.view("index");
    }


}
