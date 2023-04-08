package com.example.CookingBook.web.controllers;

import com.example.CookingBook.services.UserService;
import com.example.CookingBook.web.annotations.PageTitle;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class LoginController extends BaseController {
  private final UserService userService;

  public LoginController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/login")
  @PageTitle("Login")
  public String getLogin() {
    return "auth/login";
  }


  @PostMapping("/login-error")
  public ModelAndView onFailedLogin( @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
                                    RedirectAttributes redirectAttributes) {

    redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
    if (userService.getUserByEmailOptional(username).isPresent()) {
      if (!userService.getUserByEmailOptional(username).get().getActive()) {
        redirectAttributes.addFlashAttribute("not_active", true);
      }
      else {
        redirectAttributes.addFlashAttribute("bad_credentials", true);
      }
    }
    else {
      redirectAttributes.addFlashAttribute("bad_credentials", true);
    }

    return super.redirect("/auth/login");
  }


}
