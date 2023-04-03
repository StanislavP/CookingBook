package com.example.CookingBook.web.controllers;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class LoginController {

  @GetMapping("/login")
  public String getLogin() {
    return "auth/login";
  }


  @PostMapping("/login-error")
  public String onFailedLogin(
      @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
      RedirectAttributes redirectAttributes) {

    redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
    redirectAttributes.addFlashAttribute("bad_credentials", true);

    return "redirect:/auth/login";
  }


}