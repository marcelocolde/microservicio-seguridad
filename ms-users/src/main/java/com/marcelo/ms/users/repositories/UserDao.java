package com.marcelo.ms.users.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.marcelo.ms.users.models.entities.User;

public interface UserDao extends CrudRepository<User, Long>{
	
	Optional<User> findByUsername(String username);

}
