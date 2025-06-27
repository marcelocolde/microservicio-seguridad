package com.marcelo.ms.users.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.marcelo.ms.users.models.entities.Role;

public interface RoleDao extends CrudRepository<Role, Long>{

	Optional<Role> findByName(String name);
	
}
