package com.gbenini.UserManager.repository;

import com.gbenini.UserManager.model.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
