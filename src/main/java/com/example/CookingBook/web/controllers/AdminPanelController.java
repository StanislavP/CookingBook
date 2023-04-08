package com.example.CookingBook.web.controllers;

import com.example.CookingBook.enums.UserRoles;
import com.example.CookingBook.models.DTO.UserChangeRoleDTO;
import com.example.CookingBook.models.entity.RoleEntity;
import com.example.CookingBook.models.entity.UserEntity;
import com.example.CookingBook.repository.RoleRepository;
import com.example.CookingBook.services.UserService;
import com.example.CookingBook.web.annotations.PageTitle;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminPanelController extends BaseController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public AdminPanelController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/settings")
    @PageTitle("Admin panel")
    public String getAdminPanel(Model model) {
        return "admin/settings";
    }

    @PostMapping("/change")
    public String changeRole(@ModelAttribute UserChangeRoleDTO userChangeRoleDTO, RedirectAttributes redirectAttributes) {

        Optional<UserEntity> userEntityOptional = userService.getUserByEmailOptional(userChangeRoleDTO.getEmail());
        if (userEntityOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("EmailError",true);
            return "redirect:/admin/settings";
        }
        UserEntity user = userEntityOptional.get();
        user.setRoles(new ArrayList<>());

        if (userChangeRoleDTO.getUserRole() != null) {
            switch (userChangeRoleDTO.getUserRole()) {
                case ADMIN -> {
                    user.setRoles(roleRepository.findAll());
                }
                case MODERATOR -> {
                    RoleEntity role = roleRepository.findByUserRole(UserRoles.MODERATOR)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
                    List<RoleEntity> roleEntities = new ArrayList<>();
                    roleEntities.add(role);
                    user.setRoles(roleEntities);
                }
            }
        }


        userService.save(user);
        return "redirect:/admin/settings";
    }

    @ModelAttribute(name = "userChangeRoleDTO")
    public UserChangeRoleDTO userChangeRoleDTO() {
        return new UserChangeRoleDTO();
    }

}
