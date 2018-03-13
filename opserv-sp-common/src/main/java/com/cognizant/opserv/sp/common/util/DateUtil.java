package com.cognizant.opserv.sp.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * ****************************************************************************.
 *
 * @class DateUtil to DateUtil
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 27/11/2014
 * ***************************************************************************
 */
public class DateUtil {
	
	/**
	 * Instantiates a new date util.
	 */
	private DateUtil(){
		
	}

	/** The Constant _localeTimeZone. */
	private static final Map<Locale, String> _localeTimeZone;
	
	/** The Constant DEFAULT_DATE_FORMAT. */
	private static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";
	
	/** The Constant DEFAULT_DATE_TIME_FORMAT. */
	private static final String DEFAULT_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";
	
	/** The Constant DEFAULT_TIME_FORMAT. */
	private static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
	
	/** The Constant DEFAULT_DATE_MONTH_FORMAT. */
	private static final String DEFAULT_DATE_MONTH_FORMAT = "MMM d yyyy HH:mm:ss";
	
	/** The Constant DEFAULT_DATE_FORMAT. */
	private static final String DEFAULT_DATE_FORMAT2 = "MM-dd-yyyy";
	// Mapping Locale to TimeZone
	static {
		_localeTimeZone = new HashMap<Locale, String>();
		_localeTimeZone.put(Locale.CANADA, "Canada/Pacific");
		_localeTimeZone.put(Locale.US, "PST");
		_localeTimeZone.put(Locale.ENGLISH, "PST");
		_localeTimeZone.put(Locale.ROOT, "UTC");
	}

	/**
	 * Gets the time zone.
	 *
	 * @param locale the locale
	 * @return the time zone
	 */
	public static TimeZone getTimeZone(final Locale locale) {
		return TimeZone
				.getTimeZone(_localeTimeZone.containsKey(locale) ? _localeTimeZone
						.get(locale) : _localeTimeZone.get(Locale.US));
	}

	/**
	 * Gets the current date.
	 *
	 * @return the current date
	 */
	public static Date getCurrentDate() {
		return new Date();
	}
	
	
	//UTC DATE TO LOCAL DATE
	/**
	 * To local date.
	 *
	 * @param date the date
	 * @param locale the locale
	 * @return the string
	 */
	public static String toLocalDate(final Date date, final Locale locale){
		
		 TimeZone timeZone = TimeZone.getTimeZone(_localeTimeZone.get(Locale.ROOT));
         Calendar calTZ= new GregorianCalendar(timeZone);
         calTZ.setTimeInMillis(date.getTime());
		  
         Calendar calendar=Calendar.getInstance();
		 // Calendar calendar=Calendar.getInstance(TimeZone.getTimeZone(_localeTimeZone.get(Locale.ROOT)) );
		 calendar.set(Calendar.DATE, calTZ.get(Calendar.DATE));
		 calendar.set(Calendar.MONTH, calTZ.get(Calendar.MONTH));
		 calendar.set(Calendar.YEAR, calTZ.get(Calendar.YEAR));
          
         Calendar localTime = new GregorianCalendar(TimeZone.getTimeZone(_localeTimeZone.get(locale)));
         localTime.setTimeInMillis(calendar.getTimeInMillis());
         Integer date1 = localTime.get(Calendar.DATE);
	     Integer month = localTime.get(Calendar.MONTH);
	     Integer year = localTime.get(Calendar.YEAR);
	     month++;
	     return date1.toString()+"/"+(month).toString()+"/"+year.toString();
	}

	//UTC TIME TO LOCAL TIME
		/**
	 * To local time.
	 *
	 * @param date the date
	 * @param locale the locale
	 * @return the string
	 */
	public static String toLocalTime(final Date date, Locale locale){
        // Create a calendar object and set it time based on the UTC
        // time zone
	    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(_localeTimeZone.get(Locale.ROOT)));
	    calendar.set(Calendar.HOUR_OF_DAY, date.getHours());
	    calendar.set(Calendar.MINUTE, date.getMinutes());
	    calendar.set(Calendar.SECOND, date.getSeconds());
	
	
	    // Create a calendar object for representing a local time zone and
	    // set the time of the calendar with the value of the localtime
	
	    Calendar localTime = new GregorianCalendar(TimeZone.getTimeZone(_localeTimeZone.get(locale)));
	    localTime.setTimeInMillis(calendar.getTimeInMillis());
	    Integer hour = localTime.get(Calendar.HOUR_OF_DAY);
	    Integer minute = localTime.get(Calendar.MINUTE);
	    Integer second = localTime.get(Calendar.SECOND);

       return hour.toString()+":"+minute.toString()+":"+second.toString();
	}
			
	
//	//UTC DATETIME TO LOCAL DATETIME
//		/**
//	 * To local date time.
//	 *
//	 * @param date the date
//	 * @param locale the locale
//	 * @return the string
//	 */
//	public static String toLocalDateTime(final Date date, final Locale locale){
//			  String returnValue=null;
//			  
//			  Calendar calendar=Calendar.getInstance(TimeZone.getTimeZone(_localeTimeZone.get(Locale.ROOT)) );
//			  calendar.set(Calendar.HOUR, date.getHours());
//			  calendar.set(Calendar.MINUTE, date.getMinutes());
//			  calendar.set(Calendar.SECOND, date.getSeconds());
//	          
//	          Calendar localTime = new GregorianCalendar(TimeZone.getTimeZone(_localeTimeZone.get(locale)));
//	          localTime.setTimeInMillis(calendar.getTimeInMillis());
//	        
//		        return returnValue;
//		}
	
	//LOCAL DATE TO UTC DATE
	/**
	 * To utc date.
	 *
	 * @param date the date
	 * @param locale the locale
	 * @return the date
	 * @throws ParseException the parse exception
	 */
	public static Date toUTCDate(final String date, final Locale locale) throws ParseException{
		
		Date returnValue=null;
        if(null!=_localeTimeZone.get(locale))
        {
          final DateFormat dFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT,locale);
          dFormat.setTimeZone(TimeZone.getTimeZone(_localeTimeZone.get(locale)));
          Date strToDate = dFormat.parse(date);
          
          TimeZone timeZone = TimeZone.getTimeZone(_localeTimeZone.get(Locale.ROOT));
          Calendar calTZ= new GregorianCalendar(timeZone);
          calTZ.setTimeInMillis(strToDate.getTime());
          
          Calendar calendar= Calendar.getInstance();
          calendar.set(Calendar.MONTH, calTZ.get(Calendar.MONTH));
          calendar.set(Calendar.DAY_OF_MONTH, calTZ.get(Calendar.DAY_OF_MONTH));
          calendar.set(Calendar.YEAR, calTZ.get(Calendar.YEAR));
          
          returnValue= calendar.getTime();
        }
        return returnValue;
	}
	
	//LOCAL TIME TO UTC TIME
	/**
	 * To utc time.
	 *
	 * @param time the time
	 * @param locale the locale
	 * @return the date
	 * @throws ParseException the parse exception
	 */
	public static Date toUTCTime(final String time, final Locale locale) throws ParseException{
		
		Date returnValue=null;
        if(null!=_localeTimeZone.get(locale))
        {
        	//Conversion from String to Date
              final DateFormat dFormat = new SimpleDateFormat(DEFAULT_TIME_FORMAT,locale);
              dFormat.setTimeZone(TimeZone.getTimeZone(_localeTimeZone.get(locale)));
              Date strTimeToDate = dFormat.parse(time);
              
              //Conversion of Date from Local to UTC
              Calendar calTZ= new GregorianCalendar(TimeZone.getTimeZone(_localeTimeZone.get(Locale.ROOT)));
              calTZ.setTimeInMillis(strTimeToDate.getTime());
              
              Calendar calendar=Calendar.getInstance();
              calendar.set(Calendar.HOUR_OF_DAY, calTZ.get(Calendar.HOUR_OF_DAY));
              calendar.set(Calendar.MINUTE, calTZ.get(Calendar.MINUTE));
              calendar.set(Calendar.SECOND, calTZ.get(Calendar.SECOND));
              
              returnValue =calendar.getTime();
        }
	      return returnValue;
	}
	
	//LOCAL DATETIME TO UTC DATETIME
	/**
	 * To utc date time.
	 *
	 * @param dateTime the date time
	 * @param locale the locale
	 * @return the date
	 * @throws ParseException the parse exception
	 */
	public static Date toUTCDateTime(final String dateTime, final Locale locale) throws ParseException{
		Date returnValue=null;
        if(null!=_localeTimeZone.get(locale))
        {
              final DateFormat dFormat = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT,locale);
              dFormat.setTimeZone(TimeZone.getTimeZone(_localeTimeZone.get(locale)));
              Date strToDate = dFormat.parse(dateTime);
              
              //Conversion of Date from Local to UTC
              TimeZone timeZone = TimeZone.getTimeZone(_localeTimeZone.get(Locale.ROOT));
              Calendar calTZ= new GregorianCalendar(timeZone);
              calTZ.setTimeInMillis(strToDate.getTime());
              
              Calendar calendar=Calendar.getInstance();
              calendar.set(Calendar.MONTH, calTZ.get(Calendar.MONTH));
              calendar.set(Calendar.DAY_OF_MONTH, calTZ.get(Calendar.DAY_OF_MONTH));
              calendar.set(Calendar.YEAR, calTZ.get(Calendar.YEAR));
              calendar.set(Calendar.HOUR_OF_DAY, calTZ.get(Calendar.HOUR_OF_DAY));
              calendar.set(Calendar.MINUTE, calTZ.get(Calendar.MINUTE));
              calendar.set(Calendar.SECOND, calTZ.get(Calendar.SECOND));
              
              returnValue= calendar.getTime();
        }
        return returnValue;
	}
	
	//LOCAL CURRENT DATETIME TO UTC DATETIME
		/**
	 * To current utc date time.
	 *
	 * @param dateTime the date time
	 * @param locale the locale
	 * @return the date
	 * @throws ParseException the parse exception
	 */
	public static Date toCurrentUTCDateTime(final Date dateTime, final Locale locale) throws ParseException{
	        Date returnValue=null;
	        if(null!=_localeTimeZone.get(locale))
	        {
	              //Conversion of Date from Local to UTC
	              TimeZone timeZone = TimeZone.getTimeZone(_localeTimeZone.get(Locale.ROOT));
	              Calendar calTZ= new GregorianCalendar(timeZone);
	              calTZ.setTimeInMillis(dateTime.getTime());
	              
	              Calendar calendar=Calendar.getInstance();
	              calendar.set(Calendar.MONTH, calTZ.get(Calendar.MONTH));
	              calendar.set(Calendar.DATE, calTZ.get(Calendar.DATE));
	              calendar.set(Calendar.YEAR, calTZ.get(Calendar.YEAR));
	              calendar.set(Calendar.HOUR_OF_DAY, calTZ.get(Calendar.HOUR_OF_DAY));
	              calendar.set(Calendar.MINUTE, calTZ.get(Calendar.MINUTE));
	              calendar.set(Calendar.SECOND, calTZ.get(Calendar.SECOND));
	              
	             returnValue=calendar.getTime();
	        }
	        return returnValue;
	        
		}
		
	/**
	 * Gets the default format.
	 *
	 * @param date the date
	 * @param locale the locale
	 * @return the default format
	 */
	public static String getDefaultFormat(final Date date, final Locale locale){
		//NOTE ENGLISH Locale giving one day less format
		/*final DateFormat dFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT,locale);
        dFormat.setTimeZone(TimeZone.getTimeZone(_localeTimeZone.get(locale)));*/
		final DateFormat dFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        //dFormat.setTimeZone(TimeZone.getTimeZone(_localeTimeZone.get(locale)));
        return dFormat.format(date);
	}
	
	/**
	 * Gets the default date time format.
	 *
	 * @param date the date
	 * @param locale the locale
	 * @return the default date time format
	 */
	public static String getDefaultDateTimeFormat(final Date date, final Locale locale){
			
			final DateFormat dFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss a");
	        return dFormat.format(date);
		}
	
	/**
	 * Gets the default db format.
	 *
	 * @param date the date
	 * @param locale the locale
	 * @return the default db format
	 */
	public static String getDefaultDBFormat(final Date date, final Locale locale){
		//NOTE ENGLISH Locale giving one day less format
		/*final DateFormat dFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT,locale);
        dFormat.setTimeZone(TimeZone.getTimeZone(_localeTimeZone.get(locale)));*/
		final DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        //dFormat.setTimeZone(TimeZone.getTimeZone(_localeTimeZone.get(locale)));
		//System.out.println(dFormat.format(date));
        return dFormat.format(date);
	}
	
	/**
	 * Gets the difference.
	 *
	 * @param sDate the s date
	 * @param eDate the e date
	 * @return the difference
	 */
	public static long getDifference(final Date sDate,final Date eDate){
		long diff = 0;
		if(eDate.after(sDate)){
			 diff=eDate.getTime()-sDate.getTime();
		}else{
			 diff=sDate.getTime()-eDate.getTime();
		}		
		return diff / (24 * 60 * 60 * 1000);
	}
	
	/**
	 * Gets the difference.
	 *
	 * @param sDate the s date
	 * @param eDate the e date
	 * @return the difference
	 */
	public static long getDateDifference(final Date sDate,final Date cDate){
		if(cDate.after(sDate)){
			long diff=sDate.getTime()-cDate.getTime();
			long diffDays = diff / (24 * 60 * 60 * 1000);
			return diffDays;
		}else{
			long diff=sDate.getTime()-cDate.getTime();
			long diffDays = diff / (24 * 60 * 60 * 1000);
			return diffDays;
		}		
	}
	
	/**
	 * Date split.
	 *
	 * @param date the date
	 * @return the string
	 */
	public static String dateSplit(String date) {
		String[] dateArray = date.split("-");
		String modDate = dateArray[1] + "/" + dateArray[2] + "/" + dateArray[0];
		return modDate;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws ParseException the parse exception
	 */
	public static void main (String []args) throws ParseException{
		toUTCDate("15/04/2014",Locale.ENGLISH);
		toCurrentUTCDateTime(new Date(), Locale.ENGLISH);//send create Date  or update Date to DB
		toUTCDateTime("04/15/2014 12:30:23",Locale.ENGLISH);
		//Date d1 = new Date("Sat Oct 25 13:24:09 IST 2014");
		toLocalDate(toUTCDateTime("04/15/2014 12:30:23",Locale.ENGLISH), Locale.ENGLISH);//while Fetching From the DB Date
		DateUtil.getCurrentDate();
		getDefaultDBFormat(new Date(),Locale.ENGLISH);
	}

	/**
	 * Gets the default month format.
	 *
	 * @param dateTime the date time
	 * @param locale the locale
	 * @return the default month format
	 */
	public static String getDefaultMonthFormat(final Date dateTime, final Locale locale){
		//NOTE ENGLISH Locale giving one day less format
		final DateFormat dFormat = new SimpleDateFormat(DEFAULT_DATE_MONTH_FORMAT);
        return dFormat.format(dateTime);
	}
	
	/**
	 * Gets the default format date.
	 *
	 * @param Date the date
	 * @param locale the locale
	 * @return the default format date
	 * @throws ParseException the parse exception
	 */
	public static Date getDefaultFormatDate(final String Date,final Locale locale) throws ParseException{
		 final DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		 return dateFormat.parse(Date);
	}
	
	/**
	 * Gets the default date time format.
	 *
	 * @param Date the date
	 * @param locale the locale
	 * @return the default date time format
	 * @throws ParseException the parse exception
	 */
	public static Date getDefaultDateTimeFormat(final String Date,final Locale locale) throws ParseException{
		 final DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
		 return dateFormat.parse(Date);
	}
	

		/**
	 * Gets the default format date.
	 *
	 * @param Date the date
	 * @return the default format date
	 * @throws ParseException the parse exception
	 */
	public static Date getDefaultFormatDate(final String Date) throws ParseException{
		 final DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT2);
		 return dateFormat.parse(Date);
	}
	
	
	/**
	 * @param effTrgtStDate
	 * @return Date
	 */
	public static Date getDateLessThanTargetStartEffDate(final Date effTrgtStDate) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(effTrgtStDate);
		calender.add(Calendar.DATE, -1);
		return calender.getTime();
	}
	
	/**
	 * @param currentDate
	 * @return Date
	 */
	public static Date getDateGreaterThanCurrentDate(final Date currentDate) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(currentDate);
		calender.add(Calendar.DATE, 1);
		calender.add(Calendar.YEAR, 1);
		calender.add(Calendar.MONTH, 0);
		return calender.getTime();
	}
}
