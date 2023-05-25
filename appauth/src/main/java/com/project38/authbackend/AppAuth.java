package com.project38.authbackend;

import com.project38.authbackend.model.ApplicationUser;
import com.project38.authbackend.model.Role;
import com.project38.authbackend.repository.RoleRepository;
import com.project38.authbackend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class AppAuth {

	public static void main(String[] args) {
		SpringApplication.run(AppAuth.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository,
						  UserRepository userRepository,
						  PasswordEncoder passwordEncoder) {

		return args -> {
			if (roleRepository.findByAuthority("ADMIN").isPresent()) return;

			Role adminRole = roleRepository.save(new Role("ADMIN"));
			List<Role> defaultRoles = List.of(
					new Role("USER"),
					new Role("PUBLISHER"),
					new Role("ARTIST")
			);

			roleRepository.saveAll(defaultRoles);

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			ApplicationUser admin = new ApplicationUser(
					1,
					"admin",
					passwordEncoder.encode("password"),
					roles);

			userRepository.save(admin);
		};
	}
}
