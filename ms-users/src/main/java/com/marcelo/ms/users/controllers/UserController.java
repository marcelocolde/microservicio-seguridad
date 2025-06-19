package com.marcelo.ms.users.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.ms.users.models.entities.User;
import com.marcelo.ms.users.services.UserService;

@RestController
// @RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public Iterable<User> listUsers() {
		return userService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		Optional<User> userOptional = userService.findById(id);
		if (userOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(userOptional.orElseThrow());
		}
		return ResponseEntity.notFound().build();

	}

	@GetMapping("/username/{username}")
	public ResponseEntity<User> getUserByName(@PathVariable String username) {
		Optional<User> userOptional = userService.findByUsername(username);
		if (userOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(userOptional.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User userNew = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userNew); // return new ResponseEntity<>(userNew,
																		// HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
//		Optional<User> userOptional = userService.update(user, id);
//		if(userUpdated.isPresent()) {
//			return ResponseEntity.status(HttpStatus.CREATED).body(userUpdated.get());	
//		}
		return userService.update(user, id).map(userUpdated -> ResponseEntity.status(HttpStatus.CREATED).body(userUpdated))
				.orElseGet(() -> ResponseEntity.notFound().build());
//			return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		if (userService.findById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		userService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
