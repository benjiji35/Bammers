package com.bam.GESTIBANKBAM.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

	public void print(PrintStream out, List l) {
		for (Object item : l) {
			out.println(item);
		}
	}

	public void print(File f, List l) {
		try (PrintStream out =
                new PrintStream(new FileOutputStream(f))) {
			print(out, l);
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.err);
		}	
	}
}
