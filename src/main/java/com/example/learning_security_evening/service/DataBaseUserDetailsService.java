package com.example.learning_security_evening.service;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.learning_security_evening.model.AppUser;
import com.example.learning_security_evening.model.Role;
import com.example.learning_security_evening.repository.UserRepository;

@Service
public class DataBaseUserDetailsService implements UserDetailsService{
	private final UserRepository userRepository;
	
    public DataBaseUserDetailsService(UserRepository userRepository) {
    	 this.userRepository = userRepository;
    }
   

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        
        var authorities = appUser.getRoles().stream()
            .map(Role::name)
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toSet());

        return new User(appUser.getUsername(), appUser.getPassword(), authorities);
    }

}
