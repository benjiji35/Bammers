package com.bam.GESTIBANKBAM.utils;

import java.util.Calendar;
import java.util.Date;

public class BAMTools {
	public static long getDiffInMillis(Date d1, Date d2) {
		return d1.getTime() - d2.getTime();
	}

	public static int getDiffInDays(Date d1, Date d2) {
		return  (int) (getDiffInMillis(d1, d2) / (24 * 3600 * 1000));
	}

	public static int getDiffInHours(Date d1, Date d2) {
		return (int) (getDiffInMillis(d1, d2) / (3600 * 1000));
	}

	public static int getDiffInMinutes(Date d1, Date d2) {
		return (int) (getDiffInMillis(d1, d2) / (60 * 1000));
	}

	public static int getDiffInSeconds(Date d1, Date d2) {
		return (int) (getDiffInMillis(d1, d2) / 1000);
	}

	public static Date createCalendar(String date) {
		Calendar cal = Calendar.getInstance();

		cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)), Integer.parseInt(date.substring(8, 10)));
		return cal.getTime();
	}

	public static Date addToCalendar(Date date, int year, int month, int days) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(date);
		cal.add(Calendar.YEAR, year);
		cal.add(Calendar.MONTH, month);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}

	public static Date addToCalendar(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		cal1.setTime(date1);
		cal2.setTime(date2);
		cal1.add(Calendar.YEAR, cal2.get(Calendar.YEAR));
		cal1.add(Calendar.MONTH, cal2.get(Calendar.MONTH));
		cal1.add(Calendar.DAY_OF_MONTH, cal2.get(Calendar.DAY_OF_MONTH));

		return cal1.getTime();
	}
}
