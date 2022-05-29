package com.tech.major.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tech.major.global.GlobalDara;
import com.tech.major.model.CustomUserDetail;
import com.tech.major.model.Role;
import com.tech.major.model.User;
import com.tech.major.repository.RoleRepository;
import com.tech.major.repository.UserRepository;
import com.tech.major.service.CustomUserDetailService;

@Controller
public class LoginController {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserRepository userRepository;
	@Autowired 
	RoleRepository roleRepository;
	
	@GetMapping("/login")
	public String login( Authentication auth) {
		
		GlobalDara.cart.clear();
		return "login";
	}
	
	@GetMapping("/register")
	public String registerGet() {
		//CustomUserDetailService cService= new CustomUserDetailService();
		//cService.loadUserByUsername(null);
		return "register";
	}
	
	@PostMapping("/register")
	public String registerPost(@ModelAttribute("user") User user ,HttpServletRequest request) throws ServletException
	{
		String password = user.getPassword();
		user.setPassword(bCryptPasswordEncoder.encode(password));
		List<Role> role =new ArrayList<>();
		role.add(roleRepository.findById(2).get());
		user.setRoles(role);
		userRepository.save(user);
		request.login(user.getEmail(), password);
		return "redirect:/";
	}

}
