package com.clearance.management.backend;

import com.clearance.management.backend.entity.ApplicationUser;
import com.clearance.management.backend.entity.Faculty;
import com.clearance.management.backend.entity.Role;
import com.clearance.management.backend.repository.ApplicationUserRepository;
import com.clearance.management.backend.repository.FacultyRepository;
import com.clearance.management.backend.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BackendApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	/**
	 * Load default Admin User to the application
	 * @param roleRepository
	 * @param userRepository
	 * @param passwordEncode
	 * @return
	 */
	@Bean
	CommandLineRunner run(RoleRepository roleRepository, ApplicationUserRepository userRepository, FacultyRepository facultyRepository, PasswordEncoder passwordEncode){
		System.out.println("command line runner");
		return args ->{
			if(roleRepository.findByAuthority("ROLE_ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ROLE_ADMIN"));
			roleRepository.save(new Role("ROLE_STUDENT"));
			roleRepository.save(new Role("ROLE_FACULTY"));
			roleRepository.save(new Role("ROLE_FACULTY_HEAD"));
			roleRepository.save(new Role("ROLE_TREASURER"));
			roleRepository.save(new Role("ROLE_CHAIRMAN"));
			roleRepository.save(new Role("ROLE_SG_ADVISER"));
			roleRepository.save(new Role("ROLE_CAMPUS_MINISTRY"));
			roleRepository.save(new Role("ROLE_GUIDANCE_OFFICE"));
			roleRepository.save(new Role("ROLE_LIBRARIAN"));
			roleRepository.save(new Role("ROLE_DISPENSARY"));
			roleRepository.save(new Role("ROLE_PROPERTY_CUSTODIAN"));
			roleRepository.save(new Role("ROLE_PREFECT_DISCIPLINE"));
			roleRepository.save(new Role("ROLE_REGISTRAR"));
			roleRepository.save(new Role("ROLE_FINANCE"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			Faculty adminFaculty = new Faculty("1", "tanstephalene@gmail.com", "Mary Stephalene", "", "Tan", "Larena Street, Poblacion, Guihulngan City");

			ApplicationUser admin = new ApplicationUser("admin", passwordEncode.encode("adminPassword"), "tanstephalene@gmail.com", roles);
			adminFaculty.setApplicationUser(admin);

			System.out.println(admin);
			facultyRepository.save(adminFaculty);
		};
	}

}
