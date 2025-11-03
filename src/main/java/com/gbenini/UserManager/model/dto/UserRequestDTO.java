package com.gbenini.UserManager.model.dto;

import com.gbenini.UserManager.model.entity.User;
import com.gbenini.UserManager.model.enumerate.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserRequestDTO(

        @NotBlank(message = "Name cannot be blank")
        String name,
        @NotBlank(message = "Email cannot be blank")
        String email,
        @NotBlank(message = "Password cannot be blank")
        String password,
        @NotNull(message = "Birth date cannot be null")
        LocalDate birthDate,
        @NotNull(message = "Role cannot be null")
        UserRole role) {

    public User toEntity() {

        User user = new User();

        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setBirthDate(this.birthDate);
        user.setRole(this.role);

        return user;

    }

}
