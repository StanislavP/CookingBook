package com.example.CookingBook.web.controllers;

import com.example.CookingBook.models.DTO.UserActivationDTO;
import com.example.CookingBook.models.DTO.UserRegisterDTO;
import com.example.CookingBook.models.entity.UserActivationLinkEntity;
import com.example.CookingBook.models.entity.UserEntity;
import com.example.CookingBook.services.UserActivationService;
import com.example.CookingBook.services.UserService;
import com.example.CookingBook.validations.userValidation.UserRegisterValidator;
import com.example.CookingBook.web.annotations.PageTitle;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class RegistrationController extends BaseController {

  private static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult.";
  private final UserService userService;
  private final UserActivationService userActivationService;
  private final UserRegisterValidator userRegisterValidator;

  public RegistrationController(UserService userService, UserActivationService userActivationService, UserRegisterValidator userRegisterValidator) {
    this.userService = userService;
    this.userActivationService = userActivationService;
    this.userRegisterValidator = userRegisterValidator;
  }

  @GetMapping("/register")
  @PageTitle("Register")
  public String getRegister() {
    return "auth/register";
  }

  @PostMapping("/register")
  public ModelAndView postRegister(
      @Valid UserRegisterDTO userRegisterForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes,
      ModelAndView modelAndView) {

    this.userRegisterValidator.validate(userRegisterForm, bindingResult);

    if (bindingResult.hasErrors()) {
      redirectAttributes
          .addFlashAttribute("userRegisterForm", userRegisterForm)
          .addFlashAttribute(BINDING_RESULT_PATH + "userRegisterForm", bindingResult);

      return redirect("/auth/register");
    }

    userService.registerUser(userRegisterForm);

    modelAndView.addObject("email", userRegisterForm.getEmail());
    return super.view("/auth/registration-success",modelAndView);

  }

  @GetMapping("/confirm-account")
  public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
  {
    UserActivationLinkEntity token = userActivationService.findByConfirmationToken(confirmationToken);

    if(token != null)
    {
      UserEntity user = userService.findByEmailIgnoreCase(token.getUser().getEmail());
      user.setActive(true);
      userService.save(user);
      modelAndView.setViewName("/auth/registration-confirm");
    }
    else
    {
      modelAndView.addObject("message","The link is invalid or broken!");
      modelAndView.setViewName("error");
    }

    return modelAndView;
  }

  @ModelAttribute(name = "userRegisterForm")
  public UserRegisterDTO initUserRegisterFormDto() {
    return new UserRegisterDTO();
  }

}
