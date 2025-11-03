package com.gbenini.UserManager.model.dto;

import com.gbenini.UserManager.model.entity.Profile;
import com.gbenini.UserManager.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfileRequestDTO(

        @NotBlank(message = "Bio is required")
        String bio,
        @NotNull(message = "User ID is required")
        Long userId) {


    public Profile toEntity(User user){

        Profile profile = new Profile();

        profile.setBio(this.bio);
        profile.setUser(user);

        return profile;

    }

}
