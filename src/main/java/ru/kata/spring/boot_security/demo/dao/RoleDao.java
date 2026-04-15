package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;
import java.util.List;

public interface RoleDao {
    void saveRole(Role role);
    void removeRoleById(long id);
    List<Role> getAllRole();
    void cleanRoleTable();
    void updateRole(Role role);
    Role getRoleById(long id);
    Role findByName(String name);
}
