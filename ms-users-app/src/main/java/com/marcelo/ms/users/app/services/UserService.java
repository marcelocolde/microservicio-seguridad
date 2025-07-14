package com.marcelo.ms.users.app.services;

import java.util.List;
import java.util.Optional;

import com.marcelo.ms.users.app.models.entities.User;

public interface UserService {

	List<User> findAll();
	
	Optional<User> findById(Long id);
	
	User save(User user);
	
	void deleteById(Long id);
	
}
