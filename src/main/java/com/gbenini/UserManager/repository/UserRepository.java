package com.gbenini.UserManager.repository;

import com.gbenini.UserManager.model.entity.User;
import com.gbenini.UserManager.model.enumerate.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);

    List<User> findByEmail(String email);

    List<User> findByPassword(String password);

    List<User> findByBirthDate(LocalDate birthDate);

    List<User> findByRole(UserRole role);



}
