package com.example.learning_security_evening.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	private final DatabaseUserDetailsService userDetailsService;
	
	public SecurityConfig(DatabaseUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
	
   @Bean
   public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
 
   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
       return config.getAuthenticationManager();
   }
   
   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	   
	   http
       // Correct CSRF handling for Spring Security 6
       .csrf(csrf -> csrf
               .ignoringRequestMatchers("/h2-console/**")
       )
       
       // Allow H2 Console frames
       .headers(headers -> headers
               .frameOptions(frame -> frame.sameOrigin())
       )
       
       // Allow H2 Console frames
       .headers(headers -> headers
               .frameOptions(frame -> frame.sameOrigin())
       )
       
       // Authorization rules
       .authorizeHttpRequests(auth -> auth
               .requestMatchers("/h2-console/**").permitAll()
               .requestMatchers("/api/public/**").permitAll()
               .requestMatchers("/api/admin/**").hasRole("Role_ADMIN")
               .requestMatchers("/api/buyer/**").hasRole("Role_BUYER")
               .anyRequest().authenticated()
       )
       
       // Login + Basic Auth
       .formLogin(Customizer.withDefaults())
       .httpBasic(Customizer.withDefaults());
	   return http.build();
	   
   }

   }

