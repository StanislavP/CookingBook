package com.example.CookingBook.web.controllers;

import com.example.CookingBook.models.DTO.UserDTO;
import com.example.CookingBook.models.DTO.UserViewDTO;
import com.example.CookingBook.models.entity.UserEntity;
import com.example.CookingBook.services.UserService;
import com.example.CookingBook.web.annotations.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserViewController {
    private final UserService userService;
private final ModelMapper modelMapper;
    public UserViewController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/profile")
    @PageTitle("User panel")
    public String getAdminPanel(Model model, Principal principal) {
        UserEntity userEntity = userService.getUserByEmail(principal.getName());
        UserViewDTO user = modelMapper.map(userEntity, UserViewDTO.class);

        model.addAttribute("userInfo", user);

        return "user/profileInfo";
    }
}
