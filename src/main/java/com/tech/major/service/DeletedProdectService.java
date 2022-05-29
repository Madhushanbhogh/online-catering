package com.tech.major.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.major.model.DeletedProdect;
import com.tech.major.repository.DeletedProdectRepository;
import com.tech.major.repository.ProductRepository;

@Service
public class DeletedProdectService {
	@Autowired
	DeletedProdectRepository deletedProdectRepository;
	public List<DeletedProdect> getAllProduct()
	{
		return deletedProdectRepository.findAll();
	}
	public void addProduct( DeletedProdect product)
	{
		deletedProdectRepository.save(product);
	}
	public void removeProductById(long id)
	{
		deletedProdectRepository.deleteById(id);
	}
	public Optional<DeletedProdect> getProductById(long id){
		return deletedProdectRepository.findById(id);
		
	}
	public List<DeletedProdect> getAllProductsByCategoryId(int id)
	{
		return deletedProdectRepository.findAllByCategory_Id(id);
	}
}
