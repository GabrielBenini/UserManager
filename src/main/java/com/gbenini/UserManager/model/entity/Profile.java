package com.gbenini.UserManager.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_profile")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    private Long profileId;

    private String bio;

}
