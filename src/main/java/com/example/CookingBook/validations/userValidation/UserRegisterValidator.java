package com.example.CookingBook.validations.userValidation;


import com.example.CookingBook.models.DTO.UserRegisterDTO;
import com.example.CookingBook.repository.UserRepository;
import com.example.CookingBook.validations.annotation.Validator;
import com.example.CookingBook.validations.constants.ValidationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

@Validator
public class UserRegisterValidator implements org.springframework.validation.Validator {

    private final UserRepository userRepository;

    @Autowired
    public UserRegisterValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegisterDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        UserRegisterDTO userRegisterBindingModel = (UserRegisterDTO) object;

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            errors.rejectValue(
                    "confirmPassword",
                    ValidationConstants.PASSWORDS_DO_NOT_MATCH,
                    ValidationConstants.PASSWORDS_DO_NOT_MATCH
            );
        }

        if (this.userRepository.findByEmail(userRegisterBindingModel.getEmail()).isPresent()) {
            errors.rejectValue(
                    "email",
                    String.format(ValidationConstants.EMAIL_ALREADY_EXISTS, userRegisterBindingModel.getEmail()),
                    String.format(ValidationConstants.EMAIL_ALREADY_EXISTS, userRegisterBindingModel.getEmail())
            );
        }
    }
}

