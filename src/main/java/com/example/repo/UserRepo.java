/**
 * 
 */
package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;

/**
 * @author AC03582
 *
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

}
