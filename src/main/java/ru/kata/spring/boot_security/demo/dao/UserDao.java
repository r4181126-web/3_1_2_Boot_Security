package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Users;
import java.util.List;

public interface UserDao {
    void saveUser(Users user);
    void removeUserById(long id);
    List<Users> getAllUsers();
    void cleanUsersTable();
    void updateUser(Users user);
    Users getUserById(long id);
    Users findByUsername(String username);
}