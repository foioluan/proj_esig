package com.br.projEsig.repository;

import java.util.List;

import com.br.projEsig.domain.User;

public interface UserRepository {
	public User findByEmail(String email);

    public void save(User user);

    public void delete(Long id);

    public List<User> findAll();

    public void update(User user, Long id);
    
    public User findByName(String name);
}

