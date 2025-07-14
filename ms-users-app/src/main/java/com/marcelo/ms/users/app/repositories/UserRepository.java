package com.marcelo.ms.users.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.marcelo.ms.users.app.models.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
