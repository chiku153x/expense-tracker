package com.iit.asdcw2.expensetracker.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppDate {
	private static final String pattern = "dd/MM/yyyy";

	public static Date getDatefromString(String dateString) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try {
			return simpleDateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
