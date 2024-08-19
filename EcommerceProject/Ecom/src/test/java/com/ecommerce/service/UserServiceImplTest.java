package com.ecommerce.service;

import com.ecommerce.dao.UserRepository;
import com.ecommerce.dto.UserDto;
import com.ecommerce.entity.Address;
import com.ecommerce.entity.User;
import com.ecommerce.exceptions.UserNotFoundException;
import com.ecommerce.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize test data
        userDto = new UserDto();
        userDto.setEmail("test@example.com");
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setPhoneNo("1234567890");
        userDto.setPassword("password");
        userDto.setRole("USER");
        userDto.setUserType("REGULAR");
        List<Address> addresses = new ArrayList<>();
        userDto.setAddresses(addresses);

        user = new User(userDto);


    }

    @Test
    void createUser_ShouldReturnUser() {
        // Stubbing passwordEncoder.encode to return "encodedPassword"
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setPassword("encodedPassword");
            return savedUser;
        });

        User createdUser = userService.createUser(userDto);

        assertNotNull(createdUser);
        assertEquals("test@example.com", createdUser.getEmail());
        assertEquals("encodedPassword", createdUser.getPassword());

        verify(passwordEncoder, times(1)).encode(any(String.class));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void updateUser_ShouldUpdateAndReturnUser() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updatedUser = userService.updateUser(1, userDto);

        assertNotNull(updatedUser);
        assertEquals("test@example.com", updatedUser.getEmail());

        verify(userRepository, times(1)).findById(anyInt());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void deleteUser_ShouldThrowException_WhenUserNotFound() {
        when(userRepository.existsById(anyInt())).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(1));

        verify(userRepository, times(1)).existsById(anyInt());
        verify(userRepository, never()).deleteById(anyInt());
    }

    @Test
    void getUserById_ShouldReturnUser() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1);

        assertNotNull(foundUser);
        assertEquals("test@example.com", foundUser.getEmail());

        verify(userRepository, times(1)).findById(anyInt());
    }

    @Test
    void getUserById_ShouldThrowException_WhenUserNotFound() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1));

        verify(userRepository, times(1)).findById(anyInt());
    }

    @Test
    void getAllUser() {
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);

        List<User> returnedUsers = userService.getAllUsers();

        assertNotNull(returnedUsers);
        assertEquals(1, returnedUsers.size());

        verify(userRepository, times(1)).findAll();
    }
}
