package com.gbenini.UserManager.controller;

import com.gbenini.UserManager.model.dto.UserRequestDTO;
import com.gbenini.UserManager.model.dto.UserResponseDTO;
import com.gbenini.UserManager.model.enumerate.UserRole;
import com.gbenini.UserManager.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO requestDTO){

        UserResponseDTO responseDTO = userService.createUser(requestDTO);
        return ResponseEntity.ok(responseDTO);

    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> listAllUsers(Pageable pageable){

        Page<UserResponseDTO> responseDTO = userService.listAllUsers(pageable);
        return ResponseEntity.ok(responseDTO);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable("userId") Long userId){

        UserResponseDTO responseDTO = userService.findById(userId);
        return ResponseEntity.ok(responseDTO);

    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUserById(@PathVariable("userId")Long userId, @RequestBody @Valid UserRequestDTO requestDTO){

        UserResponseDTO responseDTO = userService.updateUserById(userId, requestDTO);
        return ResponseEntity.ok(responseDTO);

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId")Long userId){

        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDTO>> searchUsers(
            @RequestParam(required = false)String name,
            @RequestParam(required = false)String email,
            @RequestParam(required = false)String password,
            @RequestParam(required = false)LocalDate birthDate,
            @RequestParam(required = false)UserRole role){

        List<UserResponseDTO> responseDTO = userService.searchUsers(name, email, password, birthDate, role);
        return ResponseEntity.ok(responseDTO);

    }




}
