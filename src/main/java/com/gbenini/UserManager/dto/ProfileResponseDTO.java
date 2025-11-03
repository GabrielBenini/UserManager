package com.gbenini.UserManager.dto;

import com.gbenini.UserManager.model.entity.Profile;

public record ProfileResponseDTO(Long profileId, String bio, Long userId) {

    public static ProfileResponseDTO fromEntity(Profile profile){

        return new ProfileResponseDTO(
                profile.getProfileId(),
                profile.getBio(),
                profile.getUser().getUserId()
        );

    }

}
