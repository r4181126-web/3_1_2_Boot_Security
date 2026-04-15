package ru.kata.spring.boot_security.demo.dao;

import org.apache.catalina.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoimpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void saveUser(Users user) {
       entityManager.persist(user);
    }

    @Override
    @Transactional
    public void removeUserById(long id) {
        Users users = entityManager.find(Users.class, id);
        if (users != null) {
            entityManager.remove(users);
        }
    }

    @Override
    public List<Users> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM Users u ORDER BY u.id", Users.class).getResultList();
    }

    @Override
    @Transactional
    public void cleanUsersTable() {
        Query query = entityManager.createQuery("DELETE FROM Users");
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void updateUser(Users user) {
        entityManager.merge(user);
    }

    @Override
    public Users getUserById(long id) {
        return entityManager.find(Users.class, id);
    }

    @Override
    public Users findByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT u FROM Users u WHERE u.username = :username", Users.class)
                    .setParameter("username", username).getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            return null;
        }
    }

}
