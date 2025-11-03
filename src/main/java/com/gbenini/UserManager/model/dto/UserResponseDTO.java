package com.gbenini.UserManager.model.dto;

import com.gbenini.UserManager.model.entity.User;
import com.gbenini.UserManager.model.enumerate.UserRole;

import java.time.LocalDate;

public record UserResponseDTO(Long userId, String name, String email, String password, LocalDate birthDate, UserRole role) {

    public static UserResponseDTO fromEntity(User user){

        return new UserResponseDTO(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getBirthDate(),
                user.getRole()

        );

    }

}
