package com.example.CookingBook.services;

import com.example.CookingBook.models.DTO.UserRegisterDTO;
import com.example.CookingBook.models.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.CookingBook.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public void saveAndFlush(UserEntity user) {
//        userRepository.saveAndFlush(user);
//    }
//
//    public Optional<UserEntity> findById(Long id) {
//        return userRepository.findById(id);
//    }
//
//    public Optional<UserEntity> findByUsername(String username) {
//        return userRepository.findUserByUsername(username);
//    }


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;


    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

//    public void registerUser(UserRegisterDTO registrationDTO) {
//
//        UserEntity userEntity = new UserEntity().
//                setFirstName(registrationDTO.getFirstName()).
//                setLastName(registrationDTO.getLastName()).
//                setEmail(registrationDTO.getEmail()).
//                setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
//
//        userRepository.save(userEntity);
//
//        emailService.sendRegistrationEmail(userEntity.getEmail(),
//                userEntity.getFirstName() + " " + userEntity.getLastName());
//
//        //
//    }

}

