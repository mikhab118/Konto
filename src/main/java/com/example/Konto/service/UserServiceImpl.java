package com.example.Konto.service;

import com.example.Konto.functions.UserRepository;
import com.example.Konto.user.User;
import com.example.Konto.user.Role;
import com.example.Konto.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        Role userRole = new Role("ROLE_USER");
        User user = new User(
                registrationDto.getUsername(),
                registrationDto.getFirstName(),
                registrationDto.getLastName(),
                registrationDto.getEmail(),
                registrationDto.getPassword(),
                Collections.singleton(userRole));
        return userRepository.save(user);
    }
}
