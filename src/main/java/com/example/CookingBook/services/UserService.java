package com.example.CookingBook.services;


import com.example.CookingBook.models.DTO.UserDTO;
import com.example.CookingBook.models.DTO.UserRegisterDTO;
import com.example.CookingBook.models.entity.UserActivationLinkEntity;
import com.example.CookingBook.models.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.CookingBook.repository.UserRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final ModelMapper modelMapper;
    private final UserActivationService userActivationLink;

    @Value("${server.address}")
    String serverHost;
    @Value("${server.port}")
    Integer serverPort;


    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       EmailService emailService,
                       ModelMapper modelMapper,
                       UserActivationService userActivationLink) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.modelMapper = modelMapper;
        this.userActivationLink = userActivationLink;
    }

    public void registerUser(UserRegisterDTO registrationDTO) {

        UserEntity userEntity = new UserEntity().
                setFirstName(registrationDTO.getFirstName()).
                setLastName(registrationDTO.getLastName()).
                setEmail(registrationDTO.getEmail()).
                setActive(false).
                setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        userRepository.save(userEntity);


        UserActivationLinkEntity userLink = userActivationLink.generateCodeForNewUser(userEntity);

        ;


        ;
        emailService.sendRegistrationEmail(userEntity.getEmail(),
                userEntity.getFirstName() + " " + userEntity.getLastName(),
                "http://" + serverHost + ":" + serverPort + "/auth/confirm-account?token=" + userLink.getConfirmationToken());

        //
    }

    public Optional<UserEntity> getUserByEmailOptional(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    public String getUserNameByEmail(String email) {
        Optional<UserEntity> user = userRepository.findByEmail(email);

        return user.map(userEntity -> userEntity.getFirstName() + " " + userEntity.getLastName()).orElse("");
    }

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userServiceModel -> this.modelMapper.map(userServiceModel, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserEntity findByEmailIgnoreCase(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    public void save(UserEntity user) {
        userRepository.saveAndFlush(user);
    }


}

