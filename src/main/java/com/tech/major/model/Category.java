package com.tech.major.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.stereotype.Component;

import javax.persistence.*;

import lombok.Data;

@Entity

@Component

public class Category {

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // generate id auto to store into backend
	@Column(name = "category_id") //name 
	private int id;
	

	private String name;
	
	
	

}
