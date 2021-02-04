package com.jwt_spring;

import com.jwt_spring.dao.IAppUser;
import com.jwt_spring.dao.ITask;
import com.jwt_spring.entities.AppRole;
import com.jwt_spring.entities.AppUser;
import com.jwt_spring.entities.Task;
import com.jwt_spring.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;

import java.util.stream.Stream;

@SpringBootApplication
public class SpringJwtAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJwtAngularApplication.class, args);
	}

	@Bean
	CommandLineRunner run(IAppUser iuser, AccountService accountService){
		return args -> {
			// user => edgar + password => edgarpassword => role => ADMIN
			// user => emmanuel + password => emmanuelpassword => role => ADMIN
//			AppRole role = new AppRole("ADMIN");
//			accountService.saveRole(role);
//			Stream.of("edgar","emmanuel").forEach(val->{
//				AppUser user = new AppUser(val,val+"password");
//				accountService.saveUser(user);
//				accountService.addRoleToUser(user.getEmail(),role.getLibelle());
//			});
			iuser.findAll().forEach(
					task -> {
						System.out.println("User : "+task.getEmail());
						task.getRoles().forEach(r->{
							System.out.println("Role => "+r.getLibelle());
						});
					}
			);
		};
	}

}
