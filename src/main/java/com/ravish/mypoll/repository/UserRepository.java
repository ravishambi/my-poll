package com.ravish.mypoll.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravish.mypoll.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findById(Long id);
	
	List<User> findAll();
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByEmailOrUsername(String email, String username);
	
	//List<User> findByIdIn(List<Long> userIds);
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);

}
