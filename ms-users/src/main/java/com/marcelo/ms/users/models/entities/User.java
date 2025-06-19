package com.marcelo.ms.users.models.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "usuarios")
public class User {

	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	  	@NotBlank
	    @Column(unique = true, nullable = false)
	    private String username;

	  	@NotBlank
	    @Column(nullable = false)
	    private String password;

	    private Boolean enabled;

	    @NotBlank
	    @Email
	    @Column(nullable = false, unique = true)
	    private String email;
	    
//	    @JsonIgnoreProperties({"handler","hibernateLazyInitializer"}) // anotacion especial porque suele venir con errores o datos extras al hacer un getRoles, trae problemas al serializar a json
	    @ManyToMany
	    @JoinTable(name = "users_roles",
	    		joinColumns = {@JoinColumn(name = "user_id")},
	    		inverseJoinColumns = {@JoinColumn(name = "role_id")},
	    		uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","role_id"})}) // no se pueden repetir, deber ser unicos los roles de cada user
	    private List<Role> roles;
	    
	    @Transient
	    private boolean admin;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(Boolean enabled) {
			this.enabled = enabled;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public List<Role> getRoles() {
			return roles;
		}

		public void setRoles(List<Role> roles) {
			this.roles = roles;
		}

		public boolean isAdmin() {
			return admin;
		}

		public void setAdmin(boolean admin) {
			this.admin = admin;
		}

		public Boolean getEnabled() {
			return enabled;
		}
 
	    
}
