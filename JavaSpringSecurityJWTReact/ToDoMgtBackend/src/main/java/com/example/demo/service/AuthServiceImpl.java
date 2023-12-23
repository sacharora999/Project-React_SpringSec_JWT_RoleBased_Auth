package com.example.demo.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.JwtAuthResponse;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.ToDoAPIException;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.security.JwtTokenProvider;

import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

	
	private UserRepo urepo;
	private RoleRepo rrepo;
	private PasswordEncoder encoder;
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider; 
	
	
	
	
	@Override
	public String register(RegisterDto rdto) {
	
		if(urepo.existsByUsername(rdto.getUsername())) {
			throw new ToDoAPIException(HttpStatus.BAD_REQUEST,"User Already there");
		}
		if(urepo.existsByEmail(rdto.getEmail())) {
			throw new ToDoAPIException(HttpStatus.BAD_REQUEST,"Email Already there");
		}
		
		User user = new User();
		user.setName(rdto.getName());
		user.setUsername(rdto.getUsername());
		user.setEmail(rdto.getEmail());
		user.setPassword(encoder.encode(rdto.getPassword()));
		
		
		Set<Role> roles = new HashSet<>();
		Role userrole = rrepo.findByName("ROLE_USER");
		roles.add(userrole);
		user.setRoles(roles);
		
		urepo.save(user);
		
		return "User Registered Successfully";
	}

	



	@Override
    public JwtAuthResponse login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String role=null;
        String token = jwtTokenProvider.generateToken(authentication);
        User user  = urepo.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail());
        Optional<Role> urole = user.getRoles().stream().findFirst();
        
        if(urole.isPresent()) {
        	Role useRole = urole.get();
        	role = useRole.getName();
        }
        
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setRole(role);
        jwtAuthResponse.setAccessToken(token);
        
        return jwtAuthResponse;
    }

}
