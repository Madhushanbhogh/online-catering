package com.tech.major.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.major.model.DeletedProdect;


public interface DeletedProdectRepository extends JpaRepository<DeletedProdect, Long> {
	List<DeletedProdect> findAllByCategory_Id(int id);

}
