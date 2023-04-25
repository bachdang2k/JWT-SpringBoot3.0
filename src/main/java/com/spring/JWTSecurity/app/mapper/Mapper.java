package com.spring.JWTSecurity.app.mapper;

import com.spring.JWTSecurity.domain.entity.User;
import com.spring.JWTSecurity.app.dtos.RegisterDTO;
import com.spring.JWTSecurity.app.dtos.UserDTO;
import com.spring.JWTSecurity.app.responses.ProfileResponse;

import java.util.Date;

public class Mapper {

    public static UserDTO toUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setStartTime(user.getStartTime());

        return userDTO;
    }

    public static User toUse(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAvatar(userDTO.getAvatar());
        user.setStartTime(userDTO.getStartTime());
        user.setEndTime(userDTO.getEndTime());
        user.setCreatedDate(new Date());
        user.setLastModifiedDate(new Date());

        return user;
    }

    public static User toUse(RegisterDTO dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setStartTime(new Date());
        user.setCreatedDate(new Date());
        user.setLastModifiedDate(new Date());

        return user;
    }

    public static ProfileResponse toProfileDTO(User user) {
        ProfileResponse profileDTO = new ProfileResponse();
        profileDTO.setFirstName(user.getFirstName());
        profileDTO.setLastName(user.getLastName());
        profileDTO.setUsername(user.getUsername());
        profileDTO.setEmail(user.getEmail());
        profileDTO.setPhoneNumber(user.getPhoneNumber());
        profileDTO.setAvatar(user.getAvatar());

        return profileDTO;
    }
}
