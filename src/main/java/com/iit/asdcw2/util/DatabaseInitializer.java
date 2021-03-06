package com.iit.asdcw2.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iit.asdcw2.expensetracker.domain.Category;
import com.iit.asdcw2.expensetracker.domain.User;
import com.iit.asdcw2.expensetracker.service.CategoryService;
import com.iit.asdcw2.expensetracker.service.UserService;

@Component
public class DatabaseInitializer {

	@Autowired
	UserService userService;

	@Autowired
	CategoryService categoryService;

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

		User userAdmin = null;

		List<User> aUsers = allUsers.stream().filter(f -> f.getUsername().equals("admin")).collect(Collectors.toList());
		if (aUsers.size() == 0) {
			userAdmin = new User("Admin", "Admin", "Admin", "0000000000", "admin", AppSecurity.getHash("abc123"));
			userService.save(userAdmin);
		} else {
			userAdmin = aUsers.get(0);
		}

		List<User> cUsers = allUsers.stream().filter(f -> f.getUsername().equals("chiku")).collect(Collectors.toList());
		if (cUsers.size() == 0) {
			User userChinthaka = new User("Chinthaka", "Gayan", "Chinthaka", "0000000001", "chiku",
					AppSecurity.getHash("abc123"));
			userService.save(userChinthaka);
		}

		List<User> sUsers = allUsers.stream().filter(f -> f.getUsername().equals("sheena"))
				.collect(Collectors.toList());
		if (sUsers.size() == 0) {
			User userSheena = new User("Villa", "Sheena", "Villa", "0000000002", "sheena",
					AppSecurity.getHash("abc123"));
			userService.save(userSheena);
		}

		/* Adding default categories */
		List<String> categoryList = new ArrayList<String>();
		categoryList.add("Salary");
		categoryList.add("Cloths");
		categoryList.add("Fuel");
		categoryList.add("Gifts");
		categoryList.add("Kids");
		categoryList.add("Travel");

		addDefaultCategories(categoryList, userAdmin);

	}

	private void addDefaultCategories(List<String> categories, User adminUser) {
		List<Category> allCategories = categoryService.findAll();

		categories.forEach(cat -> {
			List<Category> salaryCategories = allCategories.stream().filter(f -> f.getName().equals(cat))
					.collect(Collectors.toList());
			if (salaryCategories.size() == 0) {
				Category category = new Category();
				category.setName(cat);
				category.setDescription(cat);
				category.setUser(adminUser);
				categoryService.save(category);
			}
		});

	}
}
