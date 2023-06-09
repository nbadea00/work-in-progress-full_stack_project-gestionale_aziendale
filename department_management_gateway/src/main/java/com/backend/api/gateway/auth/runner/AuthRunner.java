package com.backend.api.gateway.auth.runner;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.backend.api.gateway.auth.entity.ERole;
import com.backend.api.gateway.auth.entity.Role;
import com.backend.api.gateway.auth.payload.RegisterDto;
import com.backend.api.gateway.auth.repository.RoleRepository;
import com.backend.api.gateway.auth.repository.UserRepository;
import com.backend.api.gateway.auth.service.AuthService;




@Component
public class AuthRunner implements ApplicationRunner {
	
	@Autowired RoleRepository roleRepository;
	@Autowired UserRepository userRepository;
	@Autowired PasswordEncoder passwordEncoder;
	@Autowired AuthService authService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Run...");
		if(roleRepository.findAll().size() <= 0) setRoleDefault();
		if(userRepository.findByUsername("admin").isEmpty()) createAdmin();	
		if(userRepository.findByUsername("moderator1").isEmpty()) for(int i = 0; i < 5; i ++) createMODERATOR(i+1);		
		if(userRepository.findByUsername("user1").isEmpty()) for(int i = 0; i < 14; i++) createUser(i+1);		
	}
	
	private void setRoleDefault() {
		Role admin = new Role();
		admin.setRoleName(ERole.ROLE_ADMIN);
		roleRepository.save(admin);
		
		Role user = new Role();
		user.setRoleName(ERole.ROLE_USER);
		roleRepository.save(user);
		
		Role moderator = new Role();
		moderator.setRoleName(ERole.ROLE_MODERATOR);
		roleRepository.save(moderator);
		
	}
	
	private void createAdmin() {
		RegisterDto newUser = RegisterDto.builder()
				.email("admin@admin.admin")
				.username("admin")
				.password("#Admin00#")
				.roles(Set.of("ROLE_USER", "ROLE_ADMIN", "ROLE_MODERATOR"))
				.build();
		authService.register(newUser);
	}
	
	private void createMODERATOR(int num) {
		RegisterDto newUser = RegisterDto.builder()
				.email("moderator" + num + "@moderator.moderator")
				.username("moderator" + num)
				.password("#Admin00#")
				.roles(Set.of("ROLE_USER", "ROLE_MODERATOR"))
				.build();
		authService.register(newUser);
	}
	
	private void createUser(int num) {
		RegisterDto newUser = RegisterDto.builder()
				.email("user" + num + "@user.user")
				.username("user" + num)
				.password("#Admin00#")
				.roles(Set.of("ROLE_USER"))
				.build();
		authService.register(newUser);
	}

}
