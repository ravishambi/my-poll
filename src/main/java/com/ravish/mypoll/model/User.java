package com.ravish.mypoll.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ravish.mypoll.audit.DateAudit;

@Entity
@Table(name="users", uniqueConstraints = {
		@UniqueConstraint(columnNames = {
				"username"
		}),
		@UniqueConstraint(columnNames = {
				"email"
		})
})
public class User extends DateAudit{
	
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	
	@NotNull
	@Size(max=20)
	//@Column(name="name", length = 20)
	private String name;
	
	@NotNull
	@Size(max=20)
	//@Column(name="user_name", length=20)
	private String username;
	
	@NotNull
	@Size(max=20)
	//@Column(name="email", length = 20, unique = true)
	@Email
	private String email;
	
	@NotNull
	//@Column(name="password", length = 20)
	private String password;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="users_roles_map", joinColumns=@JoinColumn(name="user_id", foreignKey=@ForeignKey(name="fk_user_id")),
	inverseJoinColumns=@JoinColumn(name="role_id", foreignKey=@ForeignKey(name="fk_role_id")))
	private Set<Role> roles = new HashSet<>();
	
	public User() {
		
	}
	
	public User(String name, String userName, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.username = userName;
	}
	
	public User(User userDetails) {
		this.Id = userDetails.getId();
		this.name = userDetails.getUserName();
		this.username = userDetails.getUserName();
		this.email = userDetails.getEmail();
		this.password = userDetails.getPassword();
		this.setRoles(userDetails.getRoles());
	}
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	

}
