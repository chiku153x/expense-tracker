package com.iit.asdcw2.util;

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

	public static Date now() {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		Date date = new Date();
		formatter.format(date);
		return date;
	}
}
