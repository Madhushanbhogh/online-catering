package com.tech.major.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tech.major.model.Role;

@Component
public interface RoleRepository extends JpaRepository<Role, Integer>{
	
}
