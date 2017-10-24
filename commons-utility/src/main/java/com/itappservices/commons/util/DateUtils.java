/**
 * 
 */
package com.itappservices.commons.util;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * @author irepan
 *
 */
public class DateUtils {
	/*
	 * public static void main(String[] args) {
	 * 
	 * SimpleDateFormat dateFormat = new
	 * SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
	 * System.out.println(dateFormat.format(new Date()));
	 * System.out.println(dateFormat.format(getZuluDate(new Date())));
	 * System.out.println(getDateJSONFormat(new Date()));
	 * System.out.println(getDateJSONFormat(getZuluDate(new Date())));
	 * 
	 * Date date1 = new Date(); Date date2 = new
	 * Date(date1.getTime()+11377234L);
	 * System.out.println(getDateDiff(date1,date2)); }
	 */

	/**
	 * Creates a Date with Year, Month, Day, Hrs, Min, Sec given
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 *            (optional default=0)
	 * @param min
	 *            (optional default=0)
	 * @param sec
	 *            (optional default=0)
	 * 
	 * @return Date
	 * @throws InvalidParameterException
	 */
	public static Date getDate(int year, int month, int day, int... params)
			throws InvalidParameterException {
		if (month < 1 || month > 12) {
			throw new InvalidParameterException("The given month is invalid");
		}
		if (day < 1 || day > 31) {
			throw new InvalidParameterException("The given day is invalid");
		}
		int hour = 0;
		int min = 0;
		int sec = 0;
		if (params.length > 0) {
			hour = params[0];
		}
		if (hour < 0 || hour > 24) {
			throw new InvalidParameterException("The given hour is invalid");
		}
		if (params.length > 1) {
			min = params[1];
		}
		if (min < 0 || min > 60) {
			throw new InvalidParameterException("The given min is invalid");
		}
		if (params.length > 2) {
			sec = params[2];
		}
		if (sec < 0 || sec > 60) {
			throw new InvalidParameterException("The given sec is invalid");
		}
		String dateStr = String.format("%04d-%02d-%02d %02d:%02d:%02d", year,
				month, day, hour, min, sec);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date result = null;
		try {
			result = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			throw new InvalidParameterException(e.getMessage());
		}
		return result;
	}

	public static Date getZuluDate(Date date) throws ParseException {
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat(
				"yyyy-MMM-dd HH:mm:ss");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));

		// Local time zone
		SimpleDateFormat dateFormatLocal = new SimpleDateFormat(
				"yyyy-MMM-dd HH:mm:ss");

		try {
			// Time in GMT
			return dateFormatLocal.parse(dateFormatGmt.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static String getDateJSONFormat(Date date) {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
		result = String.format("%sT%sZ", sdf.format(date), stf.format(date));
		return result;
	}

	public static String getDateDiff(Date start, Date end) {
		String result = "";
		long duration = Math.abs(end.getTime() - start.getTime());

		long days = TimeUnit.MILLISECONDS.toDays(duration);
		duration -= TimeUnit.DAYS.toMillis(days);

		long hours = TimeUnit.MILLISECONDS.toHours(duration);
		duration -= TimeUnit.HOURS.toMillis(hours);

		long minutes = TimeUnit.MILLISECONDS.toMinutes(duration);
		duration -= TimeUnit.MINUTES.toMillis(minutes);

		long seconds = TimeUnit.MILLISECONDS.toSeconds(duration);
		duration -= TimeUnit.SECONDS.toMillis(seconds);

		if (days == 0L) {
			result = String.format("%02d:%02d:%02d.%03d", hours, minutes,
					seconds, duration);
		} else {
			result = String.format("%02d Days  %02d:%02d:%02d.%03d", days,
					hours, minutes, seconds, duration);
		}
		return result;
	}

	/**
	 * @Author Irepan Chavez
	 * Method responsible for convert date using 2 formats "dd-MMM-yyyy" and "dd-MMM-yyyy HH:mm"
	 * 
	 * @param dateString
	 * @return
	 * @throws ParseException
	 * @throws InvalidParameterException
	 */
	public static Date convertDateForUniversalImport(String dateString)
			throws ParseException, InvalidParameterException {
		return convertDateForUniversalImport(dateString, Locale.ENGLISH);
	}
	/**
	 * @Author Irepan Chavez
	 * Method responsible for convert date using 2 formats "dd-MMM-yyyy" and "dd-MMM-yyyy HH:mm"
	 * 
	 * @param dateString Date String to convert from
	 * @param locale locale used to convert sample locale.SPANISH
	 * @return
	 * @throws ParseException
	 * @throws InvalidParameterException
	 */
	public static Date convertDateForUniversalImport(String dateString, Locale locale)
			throws ParseException, InvalidParameterException {
		SimpleDateFormat formatForDateAndHours = new SimpleDateFormat(
				"dd-MMM-yyyy HH:mm", locale);
		SimpleDateFormat formatForDate = new SimpleDateFormat("dd-MMM-yyyy",
				locale);
		String pattern = "^[0-9*]{1,2}-[A-Za-z]{3}-[0-9]{4}(\\s[0-9]{2}:[0-9]{2})*$";
		if (!dateString.matches(pattern)) {
			throw new InvalidParameterException("Not a valid date \"" + dateString
					+ "\"");
		}

		if (dateString.length() == 11) {
			return formatForDate.parse(dateString);
		}
		return formatForDateAndHours.parse(dateString);

	}

}
