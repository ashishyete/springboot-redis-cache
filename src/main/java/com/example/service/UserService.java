/**
 * 
 */
package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repo.UserRepo;

/**
 * @author AC03582
 *
 */
@Service
public class UserService {

	@Autowired
	UserRepo userRepo;

	public void saveUser(User user) {
		userRepo.save(user);
	}

	public List<User> getUsers() {
		return userRepo.findAll();
	}

	public User getUser(Integer id) {
		return userRepo.findById(id).get();
	}

	public void deleteUser(Integer id) {
		userRepo.deleteById(id);
	}

}
