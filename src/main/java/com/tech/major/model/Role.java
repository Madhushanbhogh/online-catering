package com.tech.major.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "roles")
public class Role {
	@Id@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false, unique = true)
	@NotEmpty 
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private List<User> users;

	public String getRoleName() {
		// TODO Auto-generated method stub
		return name;
	}

	public String getName() {
		// TODO Auto-generated method stub
		User user = new User();
		return user.getFirstName();
	}

}
