package com.example.confeo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@SpringBootApplication
public class ConfeoApplication {


	@Autowired
	public ConfeoApplication(){}

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	public static void main(String[] args) {
		SpringApplication.run(ConfeoApplication.class, args);
	}


/*	private void createUser() {
		User user1 = new User();
		user1.setEmail("email");
		user1.setPassword("password");
		userService.save(user1, Role.ROLE_ORGANIZER);
		User user = new User();
		user.setEmail("user");
		user.setPassword("password");
		userService.save(user, Role.ROLE_PARTICIPANT);
		User user2 = new User();
		user2.setEmail("user2");
		user2.setPassword("password");
		userService.save(user2, Role.ROLE_PARTICIPANT);
	}*/
}
