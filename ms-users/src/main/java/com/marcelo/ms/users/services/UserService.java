package com.marcelo.ms.users.services;

import java.util.List;
import java.util.Optional;

import com.marcelo.ms.users.models.entities.User;

public interface UserService {
	
	List<User> findAll();
	
	Optional<User> findById(Long id);
	
	Optional<User> findByUsername(String username);
	
	User save(User user);
	
	Optional<User> update(User user, Long id);
	
	void deleteById(Long id);
}
