package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Users;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<Users> getAllUsers();
    Users getUserById(long id);
    void saveUser(Users user);
    void updateUser(Users user);
    void deleteUser(long id);
    Users findByUsername(String username);
}