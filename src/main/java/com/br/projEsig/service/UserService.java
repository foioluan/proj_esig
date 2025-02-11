package com.br.projEsig.service;

import java.util.List;

import com.br.projEsig.domain.User;
import com.br.projEsig.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UserService implements UserRepository{
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("esigPU");
	
	EntityManager em = emf.createEntityManager();
	
	public List<User> findAll() {
		List<User> list = em.createQuery("FROM User", User.class).getResultList();
		
		return list;
	}
	
	public User findByEmail(String email) {
		User user = em.createQuery("FROM User WHERE email LIKE :email", User.class).setParameter("email", email).getSingleResult();

		return user;
	}
	
	public User findById(Long id) {
		return em.find(User.class, id);
	}
	
	public User findByName(String name) {
		User user = em.createQuery("FROM User WHERE name LIKE :name", User.class).setParameter("name", name).getSingleResult();

		return user;
	}
	
	public void save(User user) {
		em.getTransaction().begin();
		
		em.merge(user);
		
		em.getTransaction().commit();
	}
	
	public void delete(Long id) {
		em.getTransaction().begin();
		
		User user = this.findById(id);
		em.remove(user);
		
		em.getTransaction().commit();
	}
	
	public void update(User user, Long id) {
		em.getTransaction().begin();
		
		User updatedUser = this.findById(id);
		
		updatedUser.setEmail(user.getEmail());
		updatedUser.setName(user.getName());
		
		em.merge(updatedUser);
		
		em.getTransaction().commit();
	}
}
