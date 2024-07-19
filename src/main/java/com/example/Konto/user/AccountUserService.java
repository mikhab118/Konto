package com.example.Konto.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountUserService {

    @Autowired
    private AccountUserRepository accountUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AccountUser findByUsername(String username) {
        return accountUserRepository.findByUsername(username);
    }

    public AccountUser saveUser(AccountUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return accountUserRepository.save(user);
    }
}
