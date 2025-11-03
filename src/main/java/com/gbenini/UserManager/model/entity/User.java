package com.gbenini.UserManager.model.entity;

import com.gbenini.UserManager.model.enumerate.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String name;

    private String email;

    private String password;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    private UserRole role;

}
