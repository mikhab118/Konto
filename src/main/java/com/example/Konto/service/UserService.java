package com.example.Konto.service;

import com.example.Konto.user.User;
import com.example.Konto.web.dto.UserRegistrationDto;

public interface UserService {
    User save(UserRegistrationDto registrationDto);
}
