package com.prachi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.prachi.model.User;



public interface UserRepository extends CrudRepository<User, Long>{
	List<User> findByFirstName(String firstName);
	List<User> findByZipCode(String zipcode);
}
