package com.gbenini.UserManager.service;

import com.gbenini.UserManager.exception.ResourceNotFoundException;
import com.gbenini.UserManager.model.dto.UserRequestDTO;
import com.gbenini.UserManager.model.dto.UserResponseDTO;
import com.gbenini.UserManager.model.entity.User;
import com.gbenini.UserManager.model.enumerate.UserRole;
import com.gbenini.UserManager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO createUser(UserRequestDTO requestDTO){

        User user = requestDTO.toEntity();

        userRepository.save(user);

        return UserResponseDTO.fromEntity(user);

    }

    public Page<UserResponseDTO> listAllUsers(Pageable pageable){

        return userRepository.findAll(pageable)
                .map(UserResponseDTO::fromEntity);

    }

    public UserResponseDTO findById(Long userId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return UserResponseDTO.fromEntity(user);

    }

    public UserResponseDTO updateUserById(Long userId, UserRequestDTO requestDTO){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        user.setName(requestDTO.name());
        user.setEmail(requestDTO.email());
        user.setPassword(requestDTO.password());
        user.setBirthDate(requestDTO.birthDate());
        user.setRole(requestDTO.role());

        userRepository.save(user);

        return UserResponseDTO.fromEntity(user);

    }

    public void deleteUserById(Long userId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        userRepository.delete(user);

    }

    public List<UserResponseDTO> searchUsers(String name, String email, String password, LocalDate birthDate, UserRole role){

        if (name != null) {
            userRepository.findByName(name)
                    .stream()
                    .map(UserResponseDTO::fromEntity)
                    .toList();
        }

        if (email != null){
            userRepository.findByEmail(email)
                    .stream()
                    .map(UserResponseDTO::fromEntity)
                    .toList();
        }

        if (password != null){
            userRepository.findByPassword(password)
                    .stream()
                    .map(UserResponseDTO::fromEntity)
                    .toList();
        }

        if (birthDate != null){
            userRepository.findByBirthDate(birthDate)
                    .stream()
                    .map(UserResponseDTO::fromEntity)
                    .toList();
        }

        if (role != null){
            userRepository.findByRole(role)
                    .stream()
                    .map(UserResponseDTO::fromEntity)
                    .toList();
        }

        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::fromEntity)
                .toList();

    }




}
