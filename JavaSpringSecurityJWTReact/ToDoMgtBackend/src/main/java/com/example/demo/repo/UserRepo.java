package com.example.demo.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{
	
	User findByUsername(String username);
	Boolean existsByEmail(String email);
	User findByUsernameOrEmail(String username , String email);
	Boolean existsByUsername(String username);

}
