package com.snsite.helper;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class DateTimeHelper {
	public long getHourDuration(Date firstDate, Date secondDate) {
		long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
		return TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
}
