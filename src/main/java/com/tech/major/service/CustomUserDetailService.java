package com.tech.major.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tech.major.model.Category;
import com.tech.major.model.CustomUserDetail;
import com.tech.major.model.User;
import com.tech.major.repository.UserRepository;
 @Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//return null;
		Optional<User> user=userRepository.findUserByEmail(email);
		user.orElseThrow(()->new UsernameNotFoundException("User not found"));
		return user.map(CustomUserDetail::new).get();
	}
	public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
	

}
