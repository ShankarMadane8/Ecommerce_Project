package com.ecommerce.serviceImpl;


import java.util.List;
import java.util.Optional;

import com.ecommerce.dto.UserDto;
import com.ecommerce.exceptions.OrderNotFoundException;
import com.ecommerce.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.UserRepository;
import com.ecommerce.entity.Address;
import com.ecommerce.entity.User;
import com.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(UserDto userDto) {
        User user = new User(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        for (Address address : user.getAddresses()) {
            address.setUser(user);
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(int id, UserDto userDto) {
        User user = getUserById(id);
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNo(Long.parseLong(userDto.getPhoneNo()));
        user.setRole(userDto.getRole());
        user.setUserType(userDto.getUserType());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
