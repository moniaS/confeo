package com.example.confeo;

import com.example.confeo.model.Role;
import com.example.confeo.model.User;
import com.example.confeo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConfeoApplication {

	private final UserService userService;

	@Autowired
	public ConfeoApplication(UserService userService) {
		this.userService = userService;
		//createUser();
	}

	public static void main(String[] args) {
		SpringApplication.run(ConfeoApplication.class, args);
	}

	private void createUser() {
		User user = new User();
		user.setEmail("email");
		user.setPassword("password");
		userService.save(user, Role.ROLE_PARTICIPANT);
	}
}
