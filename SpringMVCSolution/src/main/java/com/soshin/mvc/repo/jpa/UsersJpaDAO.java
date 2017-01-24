package com.soshin.mvc.repo.jpa;

import com.soshin.mvc.model.User;
import com.soshin.mvc.repo.IUserDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository("usersJpaDao")
@Transactional
public class UsersJpaDAO implements IUserDAO {

    @PersistenceContext
    private EntityManager em;


    @Override
    public User create(final User user) {
        this.em.persist(user);

        return user;
    }

    @Override
    public boolean delete(final Integer id) {

        this.em.remove(select(id));
        return true;
    }


    @Override
    public User select(final Integer id) {
        return this.em.find(User.class, id);
    }

    @Override
    public User update(final User user) {
        return this.em.merge(user);
    }

    @Override
    public Collection<User> selectAll() {
        return this.em.createQuery("select u from User u").getResultList();
    }
}
