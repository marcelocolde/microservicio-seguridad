package com.marcelo.ms.users.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcelo.ms.users.models.entities.Role;
import com.marcelo.ms.users.models.entities.User;
import com.marcelo.ms.users.repositories.RoleDao;
import com.marcelo.ms.users.repositories.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private final UserDao userDao;

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return (List<User>) userDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) {
		return userDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	@Transactional
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(getRoles(user));
		user.setEnabled(true);
		
		return userDao.save(user);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		userDao.deleteById(id);

	}

	@Override
	public Optional<User> update(User user, Long id) {
		Optional<User> userDb = this.findById(id);
		if (userDb.isPresent()) {
			userDb.get().setUsername(user.getUsername());
			userDb.get().setEmail(user.getEmail());
			if (user.isEnabled() == null) {
				userDb.get().setEnabled(true);
			}else {
				userDb.get().setEnabled(user.isEnabled());
			}
			userDb.get().setRoles(getRoles(user));
//			List<Role> roles = new ArrayList<>();
//			Optional<Role> roleOptional = roleDao.findByName("ROLE_USUARIO");
//			roleOptional.ifPresent(role -> roles.add(role));
//			user.setRoles(roles);
			return Optional.of(userDao.save(userDb.get()));
		} else {
			return Optional.empty();
		}

	}
	
	private List<Role> getRoles(User user) {
		List<Role> roles = new ArrayList<>();
		Optional<Role> roleOptional = roleDao.findByName("ROLE_USER");
		roleOptional.ifPresent(role -> roles.add(role));
		if(user.isAdmin()){
			Optional<Role> adminRoleOptional = roleDao.findByName("ROLE_ADMIN");
			adminRoleOptional.ifPresent(role -> roles.add(role));
		}
		return roles;
	}

// Otra variante de forma declarativa
//	@Override
//	public Optional<User> update2(User user, Long id) {
//	    Optional<User> userDb = this.findById(id);
//
//	    return userDb.map(dbUser -> {
//	        dbUser.setUsername(user.getUsername());
//	        dbUser.setEmail(user.getEmail());
//	        if (user.isEnabled() != null) {
//	            dbUser.setEnabled(user.isEnabled());
//	        }
//	        return userDao.save(dbUser);
//	    });
//	}

}
