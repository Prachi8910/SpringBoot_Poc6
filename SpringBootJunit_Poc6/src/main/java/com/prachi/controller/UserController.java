package com.prachi.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prachi.model.User;
import com.prachi.repository.UserRepository;
import com.prachi.service.UserService;



@Validated
@RestController
public class UserController {
	
	private final Logger logger=LoggerFactory.getLogger(this.getClass());																																																													
	
	@Autowired
    UserService userService;
	
	@Autowired
	UserRepository repository;
	
	Map<String,User> userlist=new ConcurrentHashMap<String,User>();

	@GetMapping("/users")
	public List<User> getUsers() {
		logger.info("Getting All users");
		return userService.getUsers();
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getUser(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		userService.addUser(user);
		logger.info("User Registered Successfully");
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/users/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User user) {
		userService.updateUser(id,user);
		logger.info("User Updated successfully");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/users/delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		logger.info("User deleted successfully");
		return "User deleted with id:"+id;
	}
	
	@GetMapping(value = "/users/getFname/{firstName}")
	public List<User> findByFirstName(@PathVariable String firstName) {
		logger.info("user searched by Firstname");
		return userService.findByFirstName(firstName);
	}
	@GetMapping(value = "/users/getZipcode/{zipcode}")
	public List<User> findByZipCode(@PathVariable String zipcode) {
		logger.info("User searched By zipcode");
		return userService.findByZipCode(zipcode);
	}
	

}