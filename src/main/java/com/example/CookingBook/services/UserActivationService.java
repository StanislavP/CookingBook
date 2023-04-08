package com.example.CookingBook.services;

import com.example.CookingBook.models.DTO.UserActivationDTO;
import com.example.CookingBook.models.DTO.UserDTO;
import com.example.CookingBook.models.entity.UserActivationLinkEntity;
import com.example.CookingBook.models.entity.UserEntity;
import com.example.CookingBook.repository.UserActivationLinkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserActivationService {
    private final UserActivationLinkRepository userActivationLinkRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserActivationService(UserActivationLinkRepository userActivationLinkRepository, ModelMapper modelMapper) {
        this.userActivationLinkRepository = userActivationLinkRepository;
        this.modelMapper = modelMapper;
    }


    public UserActivationLinkEntity generateCodeForNewUser(UserEntity userEntity) {

        UserActivationLinkEntity confirmationToken = new UserActivationLinkEntity(userEntity);

        return userActivationLinkRepository.saveAndFlush(confirmationToken);
    }

    public UserActivationLinkEntity findByConfirmationToken(String confirmationToken) {
        return userActivationLinkRepository.findByConfirmationToken(confirmationToken);
    }

//    public List<UserActivationDTO> findAllUsers() {
//
//        userActivationLinkRepository.findByConfirmationToken(confirmationToken)
//
//        return userRepository.findAll()
//                .stream()
//                .map(userServiceModel -> this.modelMapper.map(userServiceModel, UserDTO.class))
//                .collect(Collectors.toList());
//    }
}
