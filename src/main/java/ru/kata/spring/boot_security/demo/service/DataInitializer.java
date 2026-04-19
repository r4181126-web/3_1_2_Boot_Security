package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.Users;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        Role adminRole = roleService.findByName("ROLE_ADMIN");
        if (adminRole == null) {
            adminRole = new Role("ROLE_ADMIN");
            roleService.saveRole(adminRole);
        }
        Role userRole = roleService.findByName("ROLE_USER");
        if (userRole == null) {
            userRole = new Role("ROLE_USER");
            roleService.saveRole(userRole);
        }
        if (userService.findByUsername("andrey") == null) {
            Users andrey = new Users();
            andrey.setUsername("andrey");
            andrey.setPassword(passwordEncoder.encode("100"));
            andrey.setName("Андрей");
            andrey.setSurName("Рясной");
            andrey.setDepartment("СНГ");
            andrey.setSalary(150000);
            andrey.setRoles(Set.of(adminRole, userRole));
            userService.saveUser(andrey);
            System.out.println("Пользователь andrey создан");
        }
        if (userService.findByUsername("vika") == null) {
            Users vika = new Users();
            vika.setUsername("vika");
            vika.setPassword(passwordEncoder.encode("100"));
            vika.setName("Вика");
            vika.setSurName("Рясная");
            vika.setDepartment("Dree");
            vika.setSalary(0);
            vika.setRoles(Set.of(userRole));
            userService.saveUser(vika);
            System.out.println("Пользователь vika создан");
        }
    }
}