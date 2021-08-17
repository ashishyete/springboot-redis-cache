/**
 * 
 */
package com.example.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.model.User;
import com.example.service.UserService;

import io.micrometer.core.instrument.config.validate.Validated.Valid;

/**
 * @author AC03582
 *
 */
@RestController
public class UserController {
	
	public static final String REDIS_CACHE="USER-CACHE";

	@Autowired
	UserService userService;

	@PostMapping("/user")
	public ResponseEntity<Object> saveUser(@RequestBody User user) {
		userService.saveUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
				.toUri();
		return new ResponseEntity<Object>(location, HttpStatus.ACCEPTED);
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getUsers();
	}

	@GetMapping("/user/{id}")
	@Cacheable(value = REDIS_CACHE, key = "#id")
	public User getUser(@PathVariable Integer id) {
		return userService.getUser(id);
	}

	@DeleteMapping("/user/{id}")
	@CacheEvict(value = REDIS_CACHE, key = "#id")
	public ResponseEntity deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
		return new ResponseEntity("User Deleted Successfully", HttpStatus.OK);
	}

}
