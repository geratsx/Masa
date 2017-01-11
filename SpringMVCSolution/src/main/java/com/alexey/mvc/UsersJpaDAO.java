package com.alexey.mvc;

import com.alexey.mvc.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository("usersJpaDao")
@Transactional
public class UsersJpaDAO implements IUserDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public User create(User user) {
        em.persist(user);


        return user;
    }

    @Override
    public boolean delete(Integer id) {

        em.remove(select(id));
        return true;
    }

    @Override
    public User select(Integer id) {
        return em.find(User.class, id);
    }

    @Override
    public User update(User user) {
        return em.merge(user);
    }

    @Override
    public Collection<User> selectAll() {
        return em.createQuery("select u from User u").getResultList();
    }
}
