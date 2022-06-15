package id.co.prudential.referral.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author irfan.farid
 */
public class DateUtil {

	static final long ONE_DAY_IN_MILL_SEC = 86400000;

	static final long ONE_MINUTE_IN_MILL_SEC = 60000;

	/**
	 * The default date format that is currently used by this class.
	 */
	public static final String DEFAULT_PERIODE_FORMAT = "yyyyMM"; // res.getString("system.general.format.date");
	
	/**
	 * The default date format that is currently used by this class.
	 */
	public static final String DEFAULT_DATE_FORMAT = "dd-MMM-yyyy"; // res.getString("system.general.format.date");

	/**
	 * The default time format that is currently used by this class.
	 */
	public static final String DEFAULT_TIME_FORMAT = "hh:mm"; // res.getString("system.general.format.time");

	/**
	 * The default date format that is currently used by this class.
	 */
	public static final String DEFAULT_SQL_FORMAT = "yyyy-MM-dd"; // res.getString("system.general.format.date");

	/**
	 * Get the current Time Stamp in specified format Format exmaple: dd-MM-yyyy,
	 * dd-MM-yyyy:hh24:mi:ss
	 * 
	 * @return String in specified format
	 */
	public static String getTodaysDateAsString(String dateFormat) {
		return toFormat(getTime(), dateFormat);
	}

	/**
	 * Get the current Time Stamp in specified format in dd-mm-yyyy
	 * 
	 * @return String in DEFAULT_DATE_FORMAT
	 */
	public static String getTodaysDateAsString() {
		return getTodaysDateAsString(DateUtil.DEFAULT_DATE_FORMAT);
	}

	/**
	 * Allocates a Date object and initializes it so that it represents the time at
	 * current time
	 * 
	 * @return Date Class
	 */
	public static Date getTodaysDate() {
		return new Date();
	}

	/**
	 * Method to Format String parameters into a specified Date Format <br>
	 * which is: <b>"dd/MM/yyyy" </b>
	 * 
	 * @param day   the day of the month between 1-31
	 * @param month the month
	 * @param year  the year minus 1900
	 * @return Date Format
	 */
	public static java.util.Date toDate(String day, String month, String year) throws ParseException {
		Date returnDate;
		try {
			year = StringUtil.nullStr(year);
			String date = day + "/" + month + "/" + year;
			returnDate = toDate(date, "dd/MM/yyyy");

		} catch (ParseException e) {
			throw e;
		}
		return returnDate;
	}

	/**
	 * To Convert a long to a Date
	 * 
	 * @param long, Date String in long format
	 * @return The new Date instance or null if dateString cannot be converted
	 */
	public static Date toDate(long time) {
		Date returnDate;
		try {
			returnDate = new Date(time);
		} catch (Exception e) {
			throw e;
		}
		return returnDate;
	}

	/**
	 * To Convert a String to a Date
	 * 
	 * @param dateString , the date string to be converted
	 * @param dateFormat , the format of the date String
	 * @return The new Date instance or null if dateString cannot be converted
	 */
	public static Date toDate(String dateString, String dateFormat) throws ParseException {
		Date returnDate;
		try {

			returnDate = (new SimpleDateFormat(dateFormat)).parse(dateString);
		} catch (ParseException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return returnDate;
	}

	/**
	 * Method to return the number of days in a month could have, given the current
	 * date
	 * 
	 * @param year  the year
	 * @param month the month
	 * @return the maximum of the given field for the current date of this Calendar
	 */
	public static int getNumberOfDaysInAMonth(int year, int month) {
		GregorianCalendar gc = new GregorianCalendar(year, month - 1, 1);
		return gc.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static int getNumberOfDaysInAMonth(Date date) {
		int month = new Integer(DateUtil.getMonth(date)).intValue();
		int year = new Integer(DateUtil.getYear(date)).intValue();
		GregorianCalendar gc = new GregorianCalendar(year, month - 1, 1);
		return gc.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Method to return the name of the month in previous year, given the current
	 * date
	 * 
	 * @param year  a numeric year
	 * @param month a numeric month
	 * @return
	 */
	public static String getPreviousMonth(int year, int month) {
		GregorianCalendar gc = new GregorianCalendar(year, month - 1, 1);
		return gc.get(Calendar.MONTH) == 0 ? (year - 1) + "-12" : year + "-" + gc.get(Calendar.MONTH);
	}

	/**
	 * Method to return the name of the month in previous year, given the current
	 * date
	 * 
	 * @param year   a String year
	 * @param montha String month
	 * @return
	 */
	public static String getPreviousMonth(String strYear, String strMonth) {
		int year = Integer.parseInt(strYear);
		int month = Integer.parseInt(strMonth);
		GregorianCalendar gc = new GregorianCalendar(year, month - 1, 1);
		return gc.get(Calendar.MONTH) == 0 ? (year - 1) + "-12" : year + "-" + gc.get(Calendar.MONTH);
	}

	/**
	 * Method that validate if a given date is the last day of the month
	 * 
	 * @param thisDate a Date Class
	 * @return true if the Current date is the last day of the month
	 */
	public static boolean isLastDayOfMonth(Date thisDate) {
		int day = new Integer(DateUtil.getDay(thisDate)).intValue();
		int month = new Integer(DateUtil.getMonth(thisDate)).intValue();
		int year = new Integer(DateUtil.getYear(thisDate)).intValue();
		return (day == getNumberOfDaysInAMonth(year, month)) ? true : false;
	}

	/**
	 * Adds the specified (signed) amount of time to the given time field, based on
	 * the calendar's rules. <br>
	 * For example, to subtract 5 days from the current time of the calendar, you
	 * can achieve it by calling: <br>
	 * 
	 * <b>add(Calendar.DATE, -5) </b>
	 * 
	 * @param monthYear a Date Class; the time field
	 * @param noOfMonth an integer; the amount of date or time to be added to the
	 *                  field
	 * @return the modified Date Class
	 */
	public static Date addMonth(Date monthYear, int noOfMonth) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(monthYear);
			// Calendar cal = (new GregorianCalendar(1900 + monthYear.getYear(),
			// monthYear.getMonth(), monthYear.getDate()));

			cal.add(Calendar.MONTH, noOfMonth);

			return cal.getTime();
		} catch (Exception e) {
			return monthYear;
		}
	}

	/**
	 * Method to get the first day of the given Date Class that will be forge into
	 * specified format
	 * 
	 * @param myDate a Date Class; that will be formated
	 * @return the modified Date Class
	 */
	public static Date firstDayMonth(Date myDate) {
		try {
			return DateUtil.toDate("01/" + DateUtil.toFormat(myDate, "MM/yyyy"), "01/MM/yyyy");
		} catch (Exception e) {
			return myDate;
		}
	}

	/**
	 * Method to get the last day of the given Date Class that will be forge into
	 * specified format
	 * 
	 * @param myDate a Date Class; that will be formated
	 * @return the modified Date Class
	 */
	public static Date lastDayMonth(Date myDate) {
		try {
			int day = getNumberOfDaysInAMonth(myDate);
			return DateUtil.toDate(day + "/" + DateUtil.toFormat(myDate, "MM/yyyy"), "01/MM/yyyy");
		} catch (Exception e) {
			return myDate;
		}
	}
	
	/**
	 * Method to get the first day of the given Date Class that will be forge into
	 * specified format
	 * 
	 * @param myDate a Date Class; that will be formated
	 * @return the modified Date Class
	 */
	public static Date firstDayYear(Date myDate) {
		try {
			return DateUtil.toDate("01", "01", DateUtil.getYear(myDate));
		} catch (Exception e) {
			return myDate;
		}
	}
	
	/**
	 * Method to get the last day of the given Date Class that will be forge into
	 * specified format
	 * 
	 * @param myDate a Date Class; that will be formated
	 * @return the modified Date Class
	 */
	public static Date lastDayYear(Date myDate) {
		try {
			Date nextYear = DateUtil.toDate("01", "01", DateUtil.getYear(addYears(myDate, 1)));
			return addDays(nextYear, -1);
		} catch (Exception e) {
			return myDate;
		}
	}

	/**
	 * Method to count the total number of days in a given year
	 * 
	 * @param year String
	 * @return a double; the total number of days
	 */
	public static double getNoOfDaysInAYear(String year) {
		int x = (new Integer(year).intValue() % 4);
		if (x == 0)
			return 366;
		else
			return 365;
	}

	public static long getNoOfDaysInAYear(Date date) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

		Date date0 = simpleDateFormat.parse(simpleDateFormat.format(date));

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date0);

		long var0 = calendar.getTimeInMillis();

		calendar.add(Calendar.YEAR, 1);

		var0 = calendar.getTimeInMillis() - var0;

		var0 = var0 / ONE_DAY_IN_MILL_SEC;

		return var0;
	}

	/**
	 * Method to format a given Date Class to a DEFAULT_DATE_FORMAT
	 * 
	 * @param date Date Class
	 * @return String of a Date
	 */
	public static String toDefaultDateFormat(Date date) {
		return toFormat(date, DEFAULT_DATE_FORMAT);
	}

	//
	// public static int getNoOfDaysBetweenDates(Date temp1, Date temp2)
	// {
	// long millSecs = DateUtil.diffTime(temp1,temp2);
	// return Math.abs(new Integer(MathUtil.divide(new
	// Double(millSecs).toString(),"86400000",0,2)).intValue());
	// }

	/**
	 * Method to add Days to a given Date Class
	 * 
	 * @param date     a Date
	 * @param noOfDays an int
	 * 
	 * @return a Date
	 */
	public static Date addDays(Date date, int noOfDays) {
		try {
			// Calendar cal = (new
			// GregorianCalendar(1900+date.getYear(),date.getMonth(),date.getDate()));
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			cal.add(Calendar.DATE, noOfDays);

			return cal.getTime();
		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * Method to add Days by current year on a given Date Class
	 * 
	 * @param date     a Date
	 * @param noOfDays an int
	 * 
	 * @return a Date
	 */
	public static Date addYears(Date date, int noOfDays) {
		try {
			// Calendar cal = (new
			// GregorianCalendar(1900+date.getYear(),date.getMonth(),date.getDate()));
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			cal.add(Calendar.YEAR, noOfDays);

			return cal.getTime();
		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * Get the date in default formats
	 * 
	 * @param Date to be converted
	 * @return Converted date in DEFAULT_DATE_FORMAT:dd-MMM-yyyy
	 */
	public static String toFormat(java.util.Date date) {
		return toFormat(date, DEFAULT_DATE_FORMAT);
	}

	/**
	 * Get the date in sql formats
	 * 
	 * @param Date to be converted
	 * @return Converted date in DEFAULT_SQL_FORMAT:yyyy-MM-dd
	 */
	public static String toSqlFormat(java.util.Date date) {
		return toFormat(date, DEFAULT_SQL_FORMAT);
	}

	/**
	 * Get the current date stamp
	 * 
	 * @return Todays Date in default timezone
	 */
	public static Date getTime() {
		return (new GregorianCalendar()).getTime();
	}

	/**
	 * Get the current Time Stamp in String format
	 * 
	 * @return String in this format yyyy-MM-dd hh24:mm:ss
	 */
	public static String getTimeStamp() {
		return toFormat(getTime(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * Get time in milli seconds
	 * 
	 * @return long Time in millis seconds
	 */
	public static long getTimeInMillis() {
		return getTime().getTime();
	}

	/**
	 * Get the date in specific formats
	 * 
	 * @param Date to be converted
	 * @return Converted date
	 */
	public static String toFormat(Date date, String dateFormat) {
		try {
			return (new SimpleDateFormat(dateFormat)).format(date);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * To check if a date String is a valid date format.
	 * 
	 * @param dateString , the date string to be verified
	 * @param dateFormat , the format of the date String
	 * @return true if the date is valid
	 */
	public static boolean isValidDate(String dateString, String dateFormat) {
		try {
			Date d = (new SimpleDateFormat(dateFormat)).parse(dateString);
			// Check for valid date content.
			if (dateString.equals(toFormat(d, dateFormat)))
				return true;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * To check if <B>upload</B> date string is a valid date format, used for upload
	 * 
	 * @param dateString
	 * @return
	 */
	public static boolean isValidUploadDate(String dateString) {
		try {
			// only 2 format allowed, either 1/9/2011 or 01/09/2011
			if (isValidDate(dateString, "M/d/yyyy") || isValidDate(dateString, "MM/dd/yyyy")) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * To get valid <B>upload</B> date based on <I>isValidUploadDate</I> rules, used
	 * for upload
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date getValidUploadDate(String dateString) {
		Date result = null;
		try {
			// only 2 format allowed, either 1/9/2011 or 01/09/2011
			if (isValidDate(dateString, "M/d/yyyy")) {
				result = toDate(dateString, "M/d/yyyy");
			}
			if (isValidDate(dateString, "MM/dd/yyyy")) {
				result = toDate(dateString, "MM/dd/yyyy");
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * Get the new date which is number of days from today.
	 * 
	 * @param days the number of days to be added
	 * @return Date the new Date instance
	 */
	public static Date getDatePlusDay(int days) {
		return (new Date(getTimeInMillis() + days * ONE_DAY_IN_MILL_SEC));
	}

	/**
	 * Get the new date which is number of days from a given date.
	 * 
	 * @param date the base date
	 * @param days the number of days to be added
	 * @return Date the new Date instance
	 */
	public static Date getDatePlusDay(Date date, int days) {
		return (new Date(date.getTime() + days * ONE_DAY_IN_MILL_SEC));
	}

	/**
	 * Get the new date which is number of days before today.
	 * 
	 * @param days the number of days to be subtracted
	 * @return Date the new Date instance
	 */
	public static Date getDateMinusDay(int days) {
		return (new Date(getTimeInMillis() - days * ONE_DAY_IN_MILL_SEC));
	}

	/**
	 * Get the new date which is number of days before the given date.
	 * 
	 * @param date the base date
	 * @param days the number of days to be subtracted
	 * @return Date the new Date instance
	 */
	public static Date getDateMinusDay(Date d, int days) {
		return (new Date(d.getTime() - days * ONE_DAY_IN_MILL_SEC));
	}

	/**
	 * Get the time diffenence in millis seconds
	 * 
	 * @param before The before date
	 * @param after  The after date
	 * @return Long The different in time
	 */
	public static long diffTime(Date before, Date after) {
		return (after.getTime() - before.getTime());
	}

	/**
	 * Get the time difference in year <b>(dt2 - dt1)</b>
	 * 
	 * @param dt1
	 * @param dt2
	 * @return Long The different in time
	 */
	public static int diffTimeInYear(Date dt1, Date dt2) {
		try {
			Calendar a = getCalendar(dt1);
			Calendar b = getCalendar(dt2);
			int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
			if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) || (a.get(Calendar.MONTH) == b.get(Calendar.MONTH)
					&& a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
				if (diff > 0) {
					diff--;
				}
			}
			return diff;

		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Get the time difference in year <b>(dt2 - dt1)</b>
	 * 
	 * @param dt1
	 * @param dt2
	 * @return Long The different in time
	 */
	public static int diffRoundYear(Date dt1, Date dt2) {
		try {
			Calendar a = getCalendar(dt1);
			Calendar b = getCalendar(dt2);
			int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
			if (b.get(Calendar.MONTH) > a.get(Calendar.MONTH)) {
				diff++;
			} else if (b.get(Calendar.MONTH) == a.get(Calendar.MONTH)) {
				if (b.get(Calendar.DATE) > a.get(Calendar.DATE)) {
					diff++;
				}
			}
			return diff;

		} catch (Exception e) {
			return 0;
		}
	}

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(date);
		return cal;
	}

	/**
	 * Get the time difference in minutes <b>(dt1 - dt2)</b>
	 * 
	 * @param dt1
	 * @param dt2
	 * @return Long The different in time
	 */
	public static int diffTimeInMinutes(Date dt1, Date dt2) {
		try {
			return (int) (dt1.getTime() - dt2.getTime()) / (1000 * 60);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Get the time stamp as String
	 * 
	 * @return String in this format [8/18/00 11:29:14 PM]
	 */
	public static String getTimeStampAsString() {
		DateFormat fmt = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM,
				java.util.Locale.getDefault());
		String formatted = fmt.format(getTime());
		return formatted;
	}

	/**
	 * Get the numbers of days from a given date
	 * 
	 * @param date The date to be parsed
	 * @return String the number of days
	 */
	public static String getDay(Date date) {
		return toFormat(date, "dd");
	}

	/**
	 * Get the month for a given date
	 * 
	 * @param date The date to be parsed
	 * @return String the current month
	 */
	public static String getMonth(Date date) {
		return toFormat(date, "MM");
	}

	/**
	 * Get the month for a given date
	 * 
	 * @param date The date to be parsed
	 * @return String the current month
	 */
	public static String getIndonesianMonth(Date date) {
		String result = "";
		switch (toFormat(date, "MM")) {
		case "01":
			result = "Jan";
			break;
		case "02":
			result = "Feb";
			break;
		case "03":
			result = "Mar";
			break;
		case "04":
			result = "Apr";
			break;
		case "05":
			result = "Mei";
			break;
		case "06":
			result = "Jun";
			break;
		case "07":
			result = "Jul";
			break;
		case "08":
			result = "Agu";
			break;
		case "09":
			result = "Sep";
			break;
		case "10":
			result = "Okt";
			break;
		case "11":
			result = "Nov";
			break;
		case "12":
			result = "Des";
			break;
		default:
			result = "Unknown Date";
			break;

		}
		return result;
	}
	
	/**
	 * Get the month for a given date
	 * 
	 * @param date The date to be parsed
	 * @return String the current month
	 */
	public static String getEnglishMonth(String month) {
		String result = "";
		switch (month) {
		case "01":
			result = "Jan";
			break;
		case "02":
			result = "Feb";
			break;
		case "03":
			result = "Mar";
			break;
		case "04":
			result = "Apr";
			break;
		case "05":
			result = "May";
			break;
		case "06":
			result = "Jun";
			break;
		case "07":
			result = "Jul";
			break;
		case "08":
			result = "Aug";
			break;
		case "09":
			result = "Sep";
			break;
		case "10":
			result = "Oct";
			break;
		case "11":
			result = "Nov";
			break;
		case "12":
			result = "Dec";
			break;
		default:
			result = "Unknown Date";
			break;

		}
		return result;
	}

	/**
	 * Get the year for a given date
	 * 
	 * @param date The date to be parsed
	 * @return String the current year
	 */
	public static String getYear(Date date) {
		return toFormat(date, "yyyy");
	}

	/**
	 * Return PM / AM
	 * 
	 * @param date
	 */
	public static String getSession(Date date) {
		int time = Integer.parseInt(toFormat(date, "HH"));
		return (time < 12) ? "AM" : "PM";
	}

	/**
	 * Return if a time has expired
	 * 
	 * @return true if yes
	 */
	public static boolean isTimeExpired(long sessionTime, int minute) {
		return (getTime().getTime() - sessionTime - (minute * ONE_MINUTE_IN_MILL_SEC)) > 0 ? true : false;

	}

	/**
	 * Check whether given date is a leap year
	 * 
	 * @param date
	 * @return true if given date is a leap year
	 */
	public static boolean isLeapYear(Date date) {
		long year = Long.parseLong(getYear(date));

		boolean result = false;

		if (year % 4 > 0)
			result = false;
		else if ((year % 100 == 0) && (year % 400 > 0))
			result = false;
		else
			result = true;

		return result;
	}

	/**
	 * Get Day of the week of given date The day-of-week is an integer value \n
	 * where 1 is Sunday, 2 is Monday, ..., and 7 is Saturday
	 * 
	 */
	public static int getDayOfWeek(Date date) {

		int year = Integer.parseInt(getYear(date));
		int month = Integer.parseInt(getMonth(date));
		int day = Integer.parseInt(getDay(date));

		Calendar gc = new GregorianCalendar(year, month - 1, day);

		return gc.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * Return the next date (plus days) from date
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getNextDate(Date date, int days) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * after - before
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static long diffDays(Date before, Date after) {
		// Get difference in milliseconds
		long diffMillis = diffTime(before, after);

		return diffMillis / 86400000;
	}

	/**
	 * @param date
	 * @return
	 */
	public static long get2KYearIn3Digit(Date date) {
		String y = getYear(date);
		String result = "0";
		if (y.startsWith("2"))
			result = y.substring(1);

		return Long.parseLong(result);
	}

	/**
	 * return true if date1 = date2, false otherwise
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isEqual(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		int day1 = cal1.get(Calendar.DAY_OF_MONTH);
		int month1 = cal1.get(Calendar.MONTH);
		int year1 = cal1.get(Calendar.YEAR);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day2 = cal2.get(Calendar.DAY_OF_MONTH);
		int month2 = cal2.get(Calendar.MONTH);
		int year2 = cal2.get(Calendar.YEAR);

		return ((day1 == day2) && (month1 == month2) && (year1 == year2));
	}

	/**
	 * return true if date1 <= date2, false otherwise
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isEqualOrAfter(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		int day1 = cal1.get(Calendar.DAY_OF_MONTH);
		int month1 = cal1.get(Calendar.MONTH);
		int year1 = cal1.get(Calendar.YEAR);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day2 = cal2.get(Calendar.DAY_OF_MONTH);
		int month2 = cal2.get(Calendar.MONTH);
		int year2 = cal2.get(Calendar.YEAR);

		if (year1 < year2) { // check year
			return true;
		} else if (year1 == year2) { // if year is equal
			if (month1 < month2) { // check month
				return true;
			} else if (month1 == month2) { // if month is equal
				if (day1 <= day2) { // check day
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * return true if date1 < date2 otherwise false
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isAfter(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		int day1 = cal1.get(Calendar.DAY_OF_MONTH);
		int month1 = cal1.get(Calendar.MONTH);
		int year1 = cal1.get(Calendar.YEAR);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day2 = cal2.get(Calendar.DAY_OF_MONTH);
		int month2 = cal2.get(Calendar.MONTH);
		int year2 = cal2.get(Calendar.YEAR);

		if (year1 < year2) { // check year
			return true;
		} else if (year1 == year2) { // if year is equal
			if (month1 < month2) { // check month
				return true;
			} else if (month1 == month2) { // if month is equal
				if (day1 < day2) { // check day
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * return true if date1 < date2 otherwise false <br>
	 * <b>include time in calculation</b>
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isAfterWithTime(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		int day1 = cal1.get(Calendar.DAY_OF_MONTH);
		int month1 = cal1.get(Calendar.MONTH);
		int year1 = cal1.get(Calendar.YEAR);
		int hour1 = cal1.get(Calendar.HOUR_OF_DAY);
		int minute1 = cal1.get(Calendar.MINUTE);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day2 = cal2.get(Calendar.DAY_OF_MONTH);
		int month2 = cal2.get(Calendar.MONTH);
		int year2 = cal2.get(Calendar.YEAR);
		int hour2 = cal2.get(Calendar.HOUR_OF_DAY);
		int minute2 = cal2.get(Calendar.MINUTE);

		if (year1 < year2) { // check year
			return true;
		} else if (year1 == year2) { // if year is equal
			if (month1 < month2) { // check month
				return true;
			} else if (month1 == month2) { // if month is equal
				if (day1 < day2) { // check day
					return true;
				} else if (day1 == day2) { // if day is equal
					if (hour1 < hour2) { // check hour
						return true;
					} else if (hour1 == hour2) { // if hour is equal
						if (minute1 < minute2) { // check minute
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * get date from format specific date
	 * 
	 * @param valueDate
	 * @return
	 */
	public static Date toDate(String valueDate) throws ParseException {
		String month, year, day;
		Date returnDate;

		if ((valueDate == null) || (valueDate.length() < 7))
			return null;

		try {
			Long.parseLong(valueDate);

			year = "2" + Integer.toString(Integer.parseInt(valueDate.substring(0, 1)) - 1) + valueDate.substring(1, 3);
			month = valueDate.substring(3, 5);
			day = valueDate.substring(5, 7);

			returnDate = toDate(day, month, year);
		} catch (ParseException e) {
			throw e;
		} catch (NumberFormatException e) {
			throw e;
		}

		return returnDate;
	}

	/**
	 * format a given date to format specific date
	 * 
	 * @param date
	 * @return
	 */
	public static String toValueDateFormat(Date date) {
		String sMonth, sYear, sDay;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int valueYear = (year % 2000) + 100;

		sYear = String.valueOf(valueYear);
		sMonth = getMonth(date);
		sDay = getDay(date);

		return sYear + sMonth + sDay;
	}

	public static boolean isWithinTimePeriod(String startTime, String endTime) {
		if ((startTime == null) || (endTime == null)) {
			return false;
		}

		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getDefault());
		cal.setTime(now);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);

		String sHour = null;
		String sMinute = null;
		String sSecond = null;
		int index = startTime.indexOf(':');
		if (index > 0) {
			sHour = startTime.substring(0, index);
			int nextIndex = startTime.indexOf(':', index + 1);
			if (index > 0) {
				sMinute = startTime.substring(index + 1, nextIndex);
				sSecond = startTime.substring(nextIndex + 1, startTime.length());
			} else {
				return false;
			}
		} else {
			return false;
		}

		String eHour = null;
		String eMinute = null;
		String eSecond = null;
		index = endTime.indexOf(':');
		if (index > 0) {
			eHour = endTime.substring(0, index);
			int nextIndex = endTime.indexOf(':', index + 1);
			if (index > 0) {
				eMinute = endTime.substring(index + 1, nextIndex);
				eSecond = endTime.substring(nextIndex + 1, startTime.length());
			} else {
				return false;
			}
		} else {
			return false;
		}

		if (StringUtil.isNumeric(sHour) && StringUtil.isNumeric(sMinute) && StringUtil.isNumeric(sSecond)
				&& StringUtil.isNumeric(eHour) && StringUtil.isNumeric(eMinute) && StringUtil.isNumeric(eSecond)) {
			int startTimeCalc = (Integer.parseInt(sHour) * 60 * 60) + (Integer.parseInt(sMinute) * 60)
					+ (Integer.parseInt(sSecond));
			int endTimeCalc = (Integer.parseInt(eHour) * 60 * 60) + (Integer.parseInt(eMinute) * 60)
					+ (Integer.parseInt(eSecond));

			int actualTimeCalc = (hour * 60 * 60) + (minute * 60) + second;

			if ((actualTimeCalc >= startTimeCalc) && (actualTimeCalc < endTimeCalc)) {
				return true;
			}
		} else {
			return false;
		}

		return false;
	}

	/**
	 * Method to add hour to a given Date Class
	 * 
	 * @param date     a Date
	 * @param noOfHour an int
	 * 
	 * @return a Date
	 */
	public static Date addHours(Date date, int hour) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			cal.add(Calendar.HOUR, hour);

			return cal.getTime();
		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * Method to add minutes to a given Date Class
	 * 
	 * @param date     a Date
	 * @param noOfDays an int
	 * 
	 * @return a Date
	 */
	public static Date addMinutes(Date date, int minutes) {
		try {
			// Calendar cal = (new
			// GregorianCalendar(1900+date.getYear(),date.getMonth(),date.getDate()));
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			cal.add(Calendar.MINUTE, minutes);

			return cal.getTime();
		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * Method to add seconds to a given Date Class
	 * 
	 * @param date        a Date
	 * @param noOfSeconds an int
	 * 
	 * @return a Date
	 */
	public static Date addSeconds(Date date, int seconds) {
		try {
			// Calendar cal = (new
			// GregorianCalendar(1900+date.getYear(),date.getMonth(),date.getDate()));
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			cal.add(Calendar.SECOND, seconds);

			return cal.getTime();
		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * return true if time1 = time2(HH:mm), false otherwise
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean isEqualTime(Date time1, Date time2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(time1);
		int hour1 = cal1.get(Calendar.HOUR_OF_DAY);
		int minute1 = cal1.get(Calendar.MINUTE);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(time2);
		int hour2 = cal2.get(Calendar.HOUR_OF_DAY);
		int minute2 = cal2.get(Calendar.MINUTE);

		return ((hour1 == hour2) && (minute1 == minute2));
	}

	/**
	 * return true if time1 < time2(HH:mm), false otherwise
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean isBeforeTime(Date time1, Date time2) {

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(time1);
		int hour1 = cal1.get(Calendar.HOUR_OF_DAY);
		int minute1 = cal1.get(Calendar.MINUTE);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(time2);
		int hour2 = cal2.get(Calendar.HOUR_OF_DAY);
		int minute2 = cal2.get(Calendar.MINUTE);

		if (hour1 == hour2) {
			return (minute1 < minute2);
		} else {
			return (hour1 < hour2);
		}
	}

	public static Date getTodayDate() {
		Calendar now = Calendar.getInstance();
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		return now.getTime();
	}

	/**
	 * check if time in valid format (hh:mm)
	 * 
	 * @param number : time to be checked
	 * @return valid/not
	 */
	public static boolean isTimeValidFormatTime(String time) {
		String[] arrTime = time.trim().split(":");
		String hour;
		String minute;

		try {

			if (arrTime.length > 1) {

				if (arrTime[0].length() > 2 || arrTime[1].length() > 2) {
					return false;
				}

				if (arrTime[0].length() == 1) {
					hour = "0" + arrTime[0];
				} else {
					hour = arrTime[0];
				}

				if (arrTime[1].length() == 1) {
					minute = "0" + arrTime[1];
				} else {
					minute = arrTime[1];
				}

				if ((Integer.parseInt(hour) >= 0 && Integer.parseInt(hour) <= 23) == false) {

					return false;
				}

				if ((Integer.parseInt(minute) >= 0 && Integer.parseInt(minute) <= 59) == false) {
					return false;
				}

			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * get month name from their numerical values
	 * 
	 * @param Integer : number of month
	 * @param Boolean : set to True to get long-version, set to false to get the
	 *                simplified name.
	 * @return the name of the respective month, or null if month number is out of
	 *         range
	 */
	public static String getMonthName(Integer month, Boolean longName) {
		String ret = "";

		if (longName) {
			switch (month) {
			case 1:
				ret = "January";
				break;
			case 2:
				ret = "February";
				break;
			case 3:
				ret = "March";
				break;
			case 4:
				ret = "April";
				break;
			case 5:
				ret = "May";
				break;
			case 6:
				ret = "June";
				break;
			case 7:
				ret = "July";
				break;
			case 8:
				ret = "August";
				break;
			case 9:
				ret = "September";
				break;
			case 10:
				ret = "October";
				break;
			case 11:
				ret = "November";
				break;
			case 12:
				ret = "December";
				break;
			}
		} else {
			switch (month) {
			case 1:
				ret = "Jan";
				break;
			case 2:
				ret = "Feb";
				break;
			case 3:
				ret = "Mar";
				break;
			case 4:
				ret = "Apr";
				break;
			case 5:
				ret = "May";
				break;
			case 6:
				ret = "Jun";
				break;
			case 7:
				ret = "Jul";
				break;
			case 8:
				ret = "Aug";
				break;
			case 9:
				ret = "Sep";
				break;
			case 10:
				ret = "Oct";
				break;
			case 11:
				ret = "Nov";
				break;
			case 12:
				ret = "Dec";
				break;
			}
		}
		return ret;
	}

	public static Boolean isTimeRangeAreOneMonth(Date date1, Date date2) {
		try {
			if (Math.abs(diffDays(date1, date2)) > 30)
				return false;
		} catch (Exception e) {
			throw e;
		}
		return true;
	}

	public static int getTotalMinutes(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
	}

	public static java.sql.Date convertToSqlDate(Date date) {
		java.sql.Date result = null;
		result = new java.sql.Date(date.getTime());
		return result;
	}

	public static java.sql.Timestamp convertToSqlTimestamp(Date date) {
		java.sql.Timestamp result = null;
		result = new java.sql.Timestamp(date.getTime());
		return result;
	}

	/**
	 * convert date to billing periode with format (yyyyMM)
	 * 
	 * @param billingPeriodeDate (date)
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal convertBillingPeriode(Date billingPeriodDate) {
		return (billingPeriodDate == null ? null : new BigDecimal(DateUtil.toFormat(billingPeriodDate, "yyyyMM")));
	}

	/**
	 * convert billing periode with format (yyyyMM) to date
	 * 
	 * @param billingPeriode (yyyyMM)
	 * @return
	 * @throws Exception
	 */
	public static Date convertBillingPeriodeToDate(Integer billingPeriod) throws Exception {
		try {
			String value = billingPeriod.toString();
			DateFormat format = new SimpleDateFormat("yyyyMM");
			format.setLenient(false); // to prevent things like February 30th
			return format.parse(value);
		} catch (Exception e) {
			throw e;
		}
	}

	public static Date getFirstDateOfBillingPeriod(Integer billingPeriod) throws Exception {
		try {
			return toDate("01" + billingPeriod.toString(), "ddyyyyMM");
		} catch (Exception e) {
			throw e;
		}
	}

	public static Date getLastDateOfBillingPeriod(Integer billingPeriod) throws Exception {
		try {
			int value = DateUtil.getNumberOfDaysInAMonth(convertBillingPeriodeToDate(billingPeriod));
			return toDate(value + billingPeriod.toString(), "ddyyyyMM");
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * set time in given date to 00:00:00
	 * 
	 * @param date The date
	 * @return The date with time set to 00:00:00
	 */
	public static Date removeTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static int diffTimeInYearClaim(Date dt1, Date dt2) {
		try {
			Calendar a = getCalendar(dt1);
			Calendar b = getCalendar(dt2);
			int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
			if (diff != 0) {
				if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) || (a.get(Calendar.MONTH) == b.get(Calendar.MONTH)
						&& a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
					diff--;
				}
			}
			return diff;

		} catch (Exception e) {
			return 0;
		}
	}

}
