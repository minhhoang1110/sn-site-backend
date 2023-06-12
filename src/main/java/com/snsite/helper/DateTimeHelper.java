package com.snsite.helper;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateTimeHelper {
	public Duration getDuration(Date firstDate, Date secondDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime startDate = LocalDateTime.parse(firstDate.toString(), formatter);
		LocalDateTime endDate = LocalDateTime.parse(secondDate.toString(), formatter);
		return Duration.between(startDate, endDate);
	}
}
