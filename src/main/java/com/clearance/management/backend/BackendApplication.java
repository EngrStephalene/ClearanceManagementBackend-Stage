package com.clearance.management.backend;

import com.clearance.management.backend.entity.ApplicationUser;
import com.clearance.management.backend.entity.Faculty;
import com.clearance.management.backend.entity.Offices;
import com.clearance.management.backend.entity.Role;
import com.clearance.management.backend.repository.ApplicationUserRepository;
import com.clearance.management.backend.repository.FacultyRepository;
import com.clearance.management.backend.repository.OfficeRepository;
import com.clearance.management.backend.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	CommandLineRunner run(RoleRepository roleRepository, ApplicationUserRepository userRepository,
						  FacultyRepository facultyRepository, PasswordEncoder passwordEncode, OfficeRepository officeRepository){
		System.out.println("command line runner");
		return args ->{
			if(roleRepository.findByAuthority("ROLE_ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ROLE_ADMIN"));
			roleRepository.save(new Role("ROLE_FACULTY"));

			//STUDENT ROLES
			roleRepository.save(new Role("ROLE_STUDENT"));
			roleRepository.save(new Role("ROLE_STUDENT_SG_PRESIDENT"));
			roleRepository.save(new Role("ROLE_STUDENT_DEPARTMENT_GOV"));

			//FACULTY ROLES
			Role campusMinistryRole = roleRepository.save(new Role("ROLE_CAMPUS_MINISTRY"));
			Role guidanceOfficeRole = roleRepository.save(new Role("ROLE_GUIDANCE_OFFICE"));
			Role librarianRole = roleRepository.save(new Role("ROLE_LIBRARIAN"));
			Role dispensaryRole = roleRepository.save(new Role("ROLE_DISPENSARY"));
			Role propertyCustodianRole = roleRepository.save(new Role("ROLE_PROPERTY_CUSTODIAN"));
			Role prefectOfDisciplineRole = roleRepository.save(new Role("ROLE_PREFECT_DISCIPLINE"));
			Role registrarRole = roleRepository.save(new Role("ROLE_REGISTRAR"));
			Role financeRole = roleRepository.save(new Role("ROLE_FINANCE"));
			roleRepository.save(new Role("ROLE_COLLEGE_DEAN"));
			roleRepository.save(new Role("ROLE_SCHOOL_DIRECTOR"));
			roleRepository.save(new Role("ROLE_DEPARTMENT_CHAIRMAN"));
			roleRepository.save(new Role("ROLE_SG_ADVISER"));

			Set<Role> adminRoles = new HashSet<>();
			adminRoles.add(adminRole);

			Set<Role> campusMinistryRoles = new HashSet<>();
			campusMinistryRoles.add(campusMinistryRole);

			Set<Role> guidanceOfficeRoles = new HashSet<>();
			guidanceOfficeRoles.add(guidanceOfficeRole);

			Set<Role> librarianRoles = new HashSet<>();
			librarianRoles.add(librarianRole);

			Set<Role> dispensaryRoles = new HashSet<>();
			dispensaryRoles.add(dispensaryRole);

			Set<Role> propertyCustodianRoles = new HashSet<>();
			propertyCustodianRoles.add(propertyCustodianRole);

			Set<Role> prefectOfDisciplineRoles = new HashSet<>();
			prefectOfDisciplineRoles.add(prefectOfDisciplineRole);

			Set<Role> registrarRoles = new HashSet<>();
			registrarRoles.add(registrarRole);

			Set<Role> financeRoles = new HashSet<>();
			financeRoles.add(financeRole);

			Faculty adminFaculty = new Faculty("1", "tanstephalene@gmail.com", "Mary Stephalene", "", "Tan", "Larena Street, Poblacion, Guihulngan City");
			Faculty campusMinistry = new Faculty("2", "munezjanjan@gmail.com", "John", "Sangilla", "Munez", "Lalibertad");
			Faculty guidanceOffice = new Faculty("3", "lynbenlot93@gmail.com", "Lyn", "", "Benlot", "Guihulngan City, Negros Oriental");
			Faculty libraryOffice = new Faculty("4", "hectorlaguatan9@gmail.com","Hector", "Sabanal","laguatan","La Libertad");
			Faculty dispensaryOffice =  new Faculty ("5","dumasapalmabelle@gmail.com","Mabelle","Balansag","Dumasapl","La Libertad");
			Faculty propertyCustodianOffice = new Faculty("6","jojomurcilla@gmail.com","Jojo","Mur"," Murcilla","Guihulngan");
			Faculty prefectOfDisciplineOffice = new Faculty("7","arombooliver@gmail.com","Oliver","John","Arombo","Guihulngan") ;
			Faculty registrarOffice = new Faculty("8","balansagcarlo01@gmail.com","John","Carlo","Balansag","Guihulngan");
			Faculty financeOffice = new Faculty("9","zekiahpagao@gmail.com","Zekiah","Labrador","Pagao","Guihulngan");

			ApplicationUser admin = new ApplicationUser("admin", passwordEncode.encode("adminPassword"), "tanstephalene@gmail.com", adminRoles);
			adminFaculty.setApplicationUser(admin);

			ApplicationUser campusMinister = new ApplicationUser("munez.janjan", passwordEncode.encode("john123"), "munezjanjan@gmail.com", campusMinistryRoles);
			campusMinistry.setApplicationUser(campusMinister);

			ApplicationUser guidance = new ApplicationUser("benlot.lyn", passwordEncode.encode("lyn123"), "lynbenlot93@gmail.com", guidanceOfficeRoles);
			guidanceOffice.setApplicationUser(guidance);

			ApplicationUser librarian = new ApplicationUser("laguatan.hector", passwordEncode.encode("hector123"), "hectorlaguatan9@gmail.com", librarianRoles);
			libraryOffice.setApplicationUser(librarian);

			ApplicationUser dispensary = new ApplicationUser("balansag.mabelle", passwordEncode.encode("mabelle123"), "dumasapalmabelle@gmail.com", dispensaryRoles);
			dispensaryOffice.setApplicationUser(dispensary);

			ApplicationUser propertyCustodian = new ApplicationUser("mur.jojo", passwordEncode.encode("jojo123"), "jojomurcilla@gmail.com", propertyCustodianRoles);
			propertyCustodianOffice.setApplicationUser(propertyCustodian);

			ApplicationUser prefectOfDiscipline = new ApplicationUser("oliver.john", passwordEncode.encode("oliver123"), "arombooliver@gmail.com", prefectOfDisciplineRoles);
			prefectOfDisciplineOffice.setApplicationUser(prefectOfDiscipline);

			ApplicationUser registrar = new ApplicationUser("balansag.carl", passwordEncode.encode("carl123"), "balansagcarlo01@gmail.com", registrarRoles);
			registrarOffice.setApplicationUser(registrar);

			ApplicationUser finance = new ApplicationUser("labrador.zekiah", passwordEncode.encode("zekiah123"), "zekiahpagao@gmail.com", financeRoles);
			financeOffice.setApplicationUser(finance);

			System.out.println(admin);
			facultyRepository.save(adminFaculty);
			facultyRepository.save(campusMinistry);
			facultyRepository.save(guidanceOffice);
			facultyRepository.save(libraryOffice);
			facultyRepository.save(dispensaryOffice);
			facultyRepository.save(propertyCustodianOffice);
			facultyRepository.save(prefectOfDisciplineOffice);
			facultyRepository.save(registrarOffice);
			facultyRepository.save(financeOffice);

			Offices officeDeptChair = new Offices("Department Chairman", 0);
			Offices officeCollegeDean =  new Offices("College Dean", 0);
			Offices officeSchoolDirector = new Offices("School Director", 0);
			Offices officeSGAdviser = new Offices("SG Adviser", 0);
			Offices officeCampusMinistry = new Offices("Campus Ministry", 1);
			Offices officeGuidanceOffice = new Offices("Guidance Office", 1);
			Offices officeLibraryInCharge = new Offices("Library in Charge",1);
			Offices officeDispensaryInCharge = new Offices("Dispensary in  Charge", 1);
			Offices officePropertyCustodian = new Offices("Property Custodian", 1);
			Offices officePrefectOfDiscipline = new Offices("Prefect of Discipline", 1);
			Offices officeRegistrar = new Offices("Registrar", 1);
			Offices officeFinance = new Offices("Finance", 1);

			List<Offices> officesForAdd = new ArrayList<>();
			officesForAdd.add(officeDeptChair);
			officesForAdd.add(officeCollegeDean);
			officesForAdd.add(officeSchoolDirector);
			officesForAdd.add(officeSGAdviser);
			officesForAdd.add(officeCampusMinistry);
			officesForAdd.add(officeGuidanceOffice);
			officesForAdd.add(officeLibraryInCharge);
			officesForAdd.add(officeDispensaryInCharge);
			officesForAdd.add(officePropertyCustodian);
			officesForAdd.add(officePrefectOfDiscipline);
			officesForAdd.add(officeRegistrar);
			officesForAdd.add(officeFinance);
			officeRepository.saveAll(officesForAdd);
		};
	}

}
