package com.marcelo.ms.users.app.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.ms.users.app.models.entities.User;
import com.marcelo.ms.users.app.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> list(){
		return userService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
	   Optional<User> optionalUser = userService.findById(id);
	   if(optionalUser.isPresent()) {
		   return ResponseEntity.ok(optionalUser.orElseThrow());
	   }
	   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "el usuario no se encontró"));
	}
	
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id , @RequestBody User userBody){
		Optional<User> userdb = userService.findById(id);
		if(userdb.isPresent()) {
			User user = userdb.get();
			user.setName(userBody.getName());
			user.setLastname(userBody.getLastname());
			user.setUsername(userBody.getUsername());
			user.setPassword(userBody.getPassword());
			user.setEmail(userBody.getEmail());
			return ResponseEntity.ok(userService.save(user));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se encontro el usuario a actualizar");
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<User> userdb = userService.findById(id);
		if(userdb.isPresent()) {
			userService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("se elimino con exito el usuario "+userdb.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se encontro el usuario a eliminar");
	}
}
