package com.prachi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.prachi.model.User;
import com.prachi.repository.UserRepository;
import com.prachi.exception.ResourceNotFoundException;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
	
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
		 
	}
	
	public Long addUser(User user) {
		 userRepository.save(user);
		 return user.getId();
	}
	
	public String deleteUser(Long id) {
		userRepository.deleteById(id);
		return "User deleted with id:"+id;
	}
	
	public List<User> findByFirstName(String firstName) {
		return userRepository.findByFirstName(firstName);
	}
	
	public User updateUser(Long id,User user) {
		return userRepository.findById(id).map(u->{
			u.setFirstName(user.getFirstName());
			u.setLastName(user.getLastName());
			u.setPhoneNumber(user.getPhoneNumber());
			u.setEmail(user.getEmail());
			u.setCity(user.getCity());
			u.setState(user.getState());
			u.setCountry(user.getCountry());
			u.setZipCode(user.getZipCode());
			return userRepository.save(u);
		}).orElseThrow(()->new ResourceNotFoundException("Id"+id+"not found"));
	}
	public List<User> findByZipCode(String zipcode) {
		return userRepository.findByZipCode(zipcode);
	}
	
	

}
