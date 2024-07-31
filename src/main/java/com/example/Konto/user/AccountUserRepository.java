package com.example.Konto.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByPassword(String password);
    User findByEmail(String email);
}
