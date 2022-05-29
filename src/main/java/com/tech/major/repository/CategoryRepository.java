package com.tech.major.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.major.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{// we get all the access inorder to save in database
	

}
