package com.ravish.mypoll.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ravish.mypoll.model.Role;
import com.ravish.mypoll.model.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Optional<Role> findByName(RoleName roleName);

}
