package com.pwa.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {
	public static void main(String[] args) {
		Date x= new Date();
		Date a=  getReplaceDayDate(x, 2);
		System.out.println(formatThaiDate(x));
		System.out.println(formatThaiDate(a));
	}
	
	/**
	 * in: date1 = 23/02/2552, day =20
	 * return: 20/02/2552 
	 */
	public static Date getReplaceDayDate(Date date1, int day) {
		Date r= null;
		Calendar cal1 = new GregorianCalendar();		
		cal1.setTime(date1);
		cal1.set(Calendar.DAY_OF_MONTH, day);
		r =cal1.getTime();		
		return r;
	}
	
	
	public static int getDateDiff(Date date1, Date date2) {
		int r = 0;
		Calendar cal1 = new GregorianCalendar();
		cal1.setTime(date1);
		Calendar cal2 = new GregorianCalendar();
		cal2.setTime(date2);
		
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);
		r = day1 - day2;
		
		return r;
	}
	
	public static Date getAddDate(Date date1, int day) {
		Date r= null;
		Calendar cal1 = new GregorianCalendar();
		cal1.setTime(date1);		
		cal1.add(Calendar.DATE, day);
		r =cal1.getTime();		
		return r;
	}

	/**
	 *in = 01 ���Ҥ� 2551 from calendar
	 */
	public static Date parseThaiDate(String ddMMMyyyyStr) throws ParseException {
		Date d = null;
		if (!CUtil.isBlank(ddMMMyyyyStr)) {
			// d = new SimpleDateFormat("dd/M/yyyy", Env.LOCALE_TH).parse(ddMyyyySLStr);
			d = new SimpleDateFormat("dd MMM yyyy", Env.LOCALE_TH).parse(ddMMMyyyyStr);
		}
		// System.out.println(d);
		return d;
	}

	/**
	 *display in web page ex: 31 �.�. 2251
	 */
	public static String formatThaiDate(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat("dd MMM yyyy", Env.LOCALE_TH).format(date);
	}
	/**
	 *display in web page ex: 31 ���Ҥ� 2251
	 */
	public static String formatLongThaiDate(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat("dd MMMM yyyy", Env.LOCALE_TH).format(date);
	}

	// in =30/1/2551 from interface file
	public static Date parseEngDateFromFile(String ddMMyyyySLStr) throws ParseException {
		Date d = null;
		if (!CUtil.isBlank(ddMMyyyySLStr)) {
			d = new SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(ddMMyyyySLStr);
		}
		// System.out.println(d);
		return d;
	}

	/**
	 *to make local filename when user upload from browser ex: 511230_113061
	 */
	public static String formatToDateTime(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat("yyMMdd_HHmmss", Env.LOCALE_TH).format(date);
	}

	/**
	 * to make exportData filename to foxpro ex: 3012.51
	 */
	public static String getExportFilename(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat("yyMMdd", Env.LOCALE_TH).format(date);
	}
	
	/**
	 * 31/01/2552
	 */
	public static String formatInvDate(Date date ) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat("dd/MM/yyyy", Env.LOCALE_TH).format(date);
	}

	/**
	 * locale th
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(pattern, Env.LOCALE_TH).format(date);
	}
}
