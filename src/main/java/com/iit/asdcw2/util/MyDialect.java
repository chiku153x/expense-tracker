package com.iit.asdcw2.util;

import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.DateType;
import org.springframework.stereotype.Component;

@Component
public class MyDialect extends MySQL5InnoDBDialect {
	public MyDialect() {
		super();
		registerFunction("DATE_ADD", new SQLFunctionTemplate(DateType.INSTANCE, "date_add(?1, INTERVAL ?2 ?3)"));

	}
}
