package com.bam.GESTIBANKBAM.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.jnetparse.security.PasswordGenerator;

public class BAMTools {
	public static boolean isUndefined(String field) {
		return field == null || field.equals("undefined");
	}

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	public static Date parseDate(String s) {
		Date d = null;

		if (s != null) {
			try {
				d = dateFormat.parse(s);
			} catch (ParseException e) {
				e.printStackTrace(System.err);
			}
		}
		return d;
	}

	public static String format(Date d) {
		return dateFormat.format(d);
	}

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

	public static void print(PrintStream out, List l, String message) {
		if (message != null) {
			out.println(message);
		}
		for (Object item : l) {
			out.println(item);
		}
	}

	public static void print(File f, List l, String message) {
		try (PrintStream out =
                new PrintStream(new FileOutputStream(f))) {
			print(out, l, message);
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.err);
		}	
	}

	public static List<String> genPasswords(int passwordLength) {
		//boolean lowercases, boolean uppercases, boolean arabicdigits, boolean hexa, boolean punct, int passwordLength
		PasswordGenerator pg = new PasswordGenerator(true, true, true, true, true, passwordLength);

		return pg.next(passwordLength);
	}

	public static String genPassword(int passwordLength) {
		List<String> list = genPasswords(passwordLength);

		return list.get(0);
	}

	public static List<String> genNames(int namesLength) {
		PasswordGenerator pg = new PasswordGenerator(false, namesLength);

		return pg.next();
	}

	public static String genName(int nameLength) {
		return genNames(nameLength).get(0);
	}

	public static boolean isLastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		int day;

		cal.setTime(date);
		day = cal.get(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, day+1);
		return (cal.get(Calendar.DAY_OF_MONTH) < day);
	}

	public static boolean isLastDayOfMonth() {
		return isLastDayOfMonth(new Date());
	}
}
