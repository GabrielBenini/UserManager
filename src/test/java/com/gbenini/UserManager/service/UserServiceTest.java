package com.gbenini.UserManager.service;

import com.gbenini.UserManager.exception.ResourceNotFoundException;
import com.gbenini.UserManager.model.dto.UserRequestDTO;
import com.gbenini.UserManager.model.dto.UserResponseDTO;
import com.gbenini.UserManager.model.entity.User;
import com.gbenini.UserManager.model.enumerate.UserRole;
import com.gbenini.UserManager.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserRequestDTO userRequestDTO;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setUserId(1L);
        user.setName("Gabriel");
        user.setEmail("gabriel@email.com");
        user.setPassword("1234");
        user.setBirthDate(LocalDate.of(2000, 1, 1));
        user.setRole(UserRole.User);

        userRequestDTO = new UserRequestDTO(
                "Gabriel",
                "gabriel@email.com",
                "1234",
                LocalDate.of(2000, 1, 1),
                UserRole.User
        );
    }

    // ------------------------ CREATE ------------------------
    @Test
    void shouldCreateUserSuccessfully() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponseDTO response = userService.createUser(userRequestDTO);

        assertNotNull(response);
        assertEquals("Gabriel", response.name());
        verify(userRepository, times(1)).save(any(User.class));
    }

    // ------------------------ LIST ALL ------------------------
    @Test
    void shouldListAllUsers() {
        Page<User> page = new PageImpl<>(List.of(user));
        when(userRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<UserResponseDTO> result = userService.listAllUsers(PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        assertEquals("Gabriel", result.getContent().get(0).name());
        verify(userRepository).findAll(any(Pageable.class));
    }

    // ------------------------ FIND BY ID ------------------------
    @Test
    void shouldFindUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserResponseDTO result = userService.findById(1L);

        assertEquals("Gabriel", result.name());
        verify(userRepository).findById(1L);
    }

    @Test
    void shouldThrowWhenUserNotFoundById() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.findById(1L));
    }

    // ------------------------ UPDATE ------------------------
    @Test
    void shouldUpdateUserSuccessfully() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponseDTO result = userService.updateUserById(1L, userRequestDTO);

        assertEquals("Gabriel", result.name());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void shouldThrowWhenUpdatingNonexistentUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.updateUserById(1L, userRequestDTO));
    }

    // ------------------------ DELETE ------------------------
    @Test
    void shouldDeleteUserSuccessfully() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        userService.deleteUserById(1L);

        verify(userRepository).delete(user);
    }

    @Test
    void shouldThrowWhenDeletingNonexistentUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUserById(1L));
    }

    // ------------------------ SEARCH ------------------------
    @Test
    void shouldSearchUserByName() {
        when(userRepository.findByName("Gabriel")).thenReturn(List.of(user));

        List<UserResponseDTO> result = userService.searchUsers("Gabriel", null, null, null, null);

        assertEquals(1, result.size());
        verify(userRepository).findByName("Gabriel");
    }

    @Test
    void shouldReturnAllUsersWhenNoFiltersProvided() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<UserResponseDTO> result = userService.searchUsers(null, null, null, null, null);

        assertEquals(1, result.size());
        verify(userRepository).findAll();
    }
}
