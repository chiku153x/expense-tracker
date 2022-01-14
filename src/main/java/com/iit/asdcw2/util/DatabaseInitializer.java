package com.iit.asdcw2.util;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {
	@PostConstruct
	public void initDatabase() {
		loadData();
	}
	
	private void loadData() {
		System.out.println("Init db....");
	}
}
