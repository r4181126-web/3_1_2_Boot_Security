package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.Users;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        Role adminRole = roleDao.findByName("ROLE_ADMIN");
        if (adminRole == null) {
            adminRole = new Role("ROLE_ADMIN");
            roleDao.saveRole(adminRole);
        }
        Role userRole = roleDao.findByName("ROLE_USER");
        if (userRole == null) {
            userRole = new Role("ROLE_USER");
            roleDao.saveRole(userRole);
        }
        if (userDao.findByUsername("andrey") == null) {
            Users andrey = new Users();
            andrey.setUsername("andrey");
            andrey.setPassword(passwordEncoder.encode("100"));
            andrey.setName("Андрей");
            andrey.setSurName("Рясной");
            andrey.setDepartment("СНГ");
            andrey.setSalary(150000);
            andrey.setRoles(Set.of(adminRole, userRole));
            userDao.saveUser(andrey);
            System.out.println("Пользователь andrey создан");
        }
        if (userDao.findByUsername("vika") == null) {
            Users vika = new Users();
            vika.setUsername("vika");
            vika.setPassword(passwordEncoder.encode("100"));
            vika.setName("Вика");
            vika.setSurName("Рясная");
            vika.setDepartment("Dree");
            vika.setSalary(0);
            vika.setRoles(Set.of(userRole));
            userDao.saveUser(vika);
            System.out.println("Пользователь vika создан");
        }
    }
}