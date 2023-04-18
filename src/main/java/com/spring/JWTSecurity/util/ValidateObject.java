package com.spring.JWTSecurity.util;

import com.spring.JWTSecurity.model.requestDTO.RegisterDTO;
import com.spring.JWTSecurity.model.requestDTO.UserDTO;

import java.util.HashMap;
import java.util.Map;

public class ValidateObject {

    public static Map<String, String> validateUserDTO(UserDTO userDTO) {

        Map<String, String> errors = new HashMap<>();

        errors.putAll(ValidateUtils.builder()
                .fieldName("first_name")
                .value(userDTO.getFirstName())
                .required(true)
                .maxLength(20)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("last_name")
                .value(userDTO.getLastName())
                .required(false)
                .maxLength(20)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("username")
                .value(userDTO.getUsername())
                .required(true)
                .isUsername(true)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("email")
                .value(userDTO.getEmail())
                .required(true)
                .isEmail(true)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("phone_number")
                .value(userDTO.getPhoneNumber())
                .required(false)
                .minLength(10)
                .maxLength(12)
                .onlyNumber(true)
                .build().validate());

        return errors;
    }

    public static Map<String, String> validateRegisterDTO(RegisterDTO registerDTO) {

        Map<String, String> errors = new HashMap<>();

        errors.putAll(ValidateUtils.builder()
                .fieldName("first_name")
                .value(registerDTO.getFirstName())
                .required(true)
                .maxLength(20)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("last_name")
                .value(registerDTO.getLastName())
                .required(false)
                .maxLength(20)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("username")
                .value(registerDTO.getUsername())
                .required(true)
                .isUsername(true)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("email")
                .value(registerDTO.getEmail())
                .required(true)
                .isEmail(true)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("password")
                .value(registerDTO.getPassword())
                .required(true)
                .isPassword(true)
                .build().validate()
        );

        return errors;
    }
}
