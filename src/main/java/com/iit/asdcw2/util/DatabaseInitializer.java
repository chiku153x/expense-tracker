package com.iit.asdcw2.util;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iit.asdcw2.expensetracker.domain.User;
import com.iit.asdcw2.expensetracker.service.UserService;

@Component
public class DatabaseInitializer {

	@Autowired
	UserService userService;

	@PostConstruct
	public void initDatabase() {
		loadData();
	}

	private void loadData() {
		System.out.println("Init db....");

		/* Adding default users */
		// Long id, String name, String firstName, String lastName, String phoneNo,
		// String username, String password
		
		List<User> allUsers = userService.findAll();
		List<User> cUsers = allUsers.stream().filter(f -> f.getUsername().equals("chiku")).collect(Collectors.toList());
		if (cUsers.size() == 0) {
			User userChinthaka = new User("Chinthaka", "Gayan", "Chinthaka", "0000000001", "chiku", AppSecurity.getHash("abc123"));
			userService.save(userChinthaka);
		}

		List<User> sUsers = allUsers.stream().filter(f -> f.getUsername().equals("sheena")).collect(Collectors.toList());
		if (sUsers.size() == 0) {
			User userSheena = new User("Villa", "Sheena", "Villa", "0000000002", "sheena", AppSecurity.getHash("abc123"));
			userService.save(userSheena);
		}

	}
}
