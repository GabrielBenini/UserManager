package com.gbenini.UserManager.repository;

import com.gbenini.UserManager.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
