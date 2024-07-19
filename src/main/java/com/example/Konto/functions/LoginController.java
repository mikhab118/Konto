package com.example.Konto.functions;

import com.example.Konto.user.AccountUser;
import com.example.Konto.user.AccountUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private AccountUserService accountUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AccountUser user) {
        if (accountUserService.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        AccountUser savedUser = accountUserService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        AccountUser existingUser = accountUserService.findByUsername(loginRequest.getUsername());

        if (existingUser == null) {
            log.info("User not found: " + loginRequest.getUsername());
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        boolean passwordMatches = passwordEncoder.matches(loginRequest.getPassword(), existingUser.getPassword());

        if (!passwordMatches) {
            log.info("Password mismatch for user: " + loginRequest.getUsername());
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        log.info("User logged in successfully: " + loginRequest.getUsername());
        return ResponseEntity.ok(existingUser); // Logowanie udane
    }
}
