package com.br.projEsig.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.br.projEsig.domain.User;
import com.br.projEsig.repository.UserRepository;

public class UserService implements UserRepository {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("esigPU");
    private EntityManager em = emf.createEntityManager();

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class)
                 .getResultList();
    }

    @Override
    public User findByEmail(String email) {
    	try {
            return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                     .setParameter("email", email)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User findByName(String name) {
        return em.createQuery("SELECT u FROM User u WHERE u.name LIKE :name", User.class)
                 .setParameter("name", name)
                 .getSingleResult();
    }

    @Override
    public void save(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Long id) {
        em.getTransaction().begin();
        User user = this.findById(id);
        if (user != null) {
            em.remove(user);
        }
        em.getTransaction().commit();
    }

    @Override
    public void update(User user, Long id) {
        em.getTransaction().begin();
        User updatedUser = this.findById(id);
        if (updatedUser != null) {
            updatedUser.setEmail(user.getEmail());
            updatedUser.setName(user.getName());
            em.merge(updatedUser);
        }
        em.getTransaction().commit();
    }
}
