package com.tech.major.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.major.model.Category;
import com.tech.major.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService { // service act as a middle ware
	
	@Autowired
	CategoryRepository categoryRepository; // to access categoryRepository data
	
	public List<Category> getAllCategory(){ // method to retrive data
		return categoryRepository.findAll();
	}
	public void addCategory(Category category) {
		categoryRepository.save(category); // it will take save data of categoryRepository into our category database
	}
	//delete method
	public void removeCategoryById( int id) {
		categoryRepository.deleteById(id);
	}
//	//update method
public Optional<Category> getCategoryById(int id) {
		return categoryRepository.findById(id);
	}
}
