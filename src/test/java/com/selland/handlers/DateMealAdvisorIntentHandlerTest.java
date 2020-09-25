package com.selland.handlers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class DateMealAdvisorIntentHandlerTest {

	@Test
	public void dateParsing() {
		
		//timestamp from Meal table : 2016-11-16T11:30:00.000Z
		
		//"Date" string from Alexa : 2019-10-14
		String dateString = "2019-05-05";
		DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(dateString, dtf);
		assertNotNull(date);
		
//		date = LocalDate.parse("2018", dtf);
//		assertNotNull(date);
//		java.time.format.DateTimeParseException: Text '2018' could not be parsed at index 4
		
//		date = LocalDate.parse("2019-05", dtf);
//		assertNotNull(date);
//		java.time.format.DateTimeParseException: Text '2018' could not be parsed at index 4
		
//		Date dateFromAWS = com.amazonaws.util.DateUtils.parseISO8601Date(dateString);
//		assertNotNull(dateFromAWS);
		
//		date.atStartOfDay(ZoneId.of("UTC")).toInstant();

		
		
//		ZonedDateTime.ofInstant(date.getTimestamp().toInstant(), ZoneId.of("UTC"));
		
	}
}
