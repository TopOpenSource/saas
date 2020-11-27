package com.sdstc.pub.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author cheng
 *
 */
public class DateUtils {

	public static String FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";
	public static String FORMAT_DATE = "yyyy-MM-dd";

	public static String getNowTimeStr() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(new Date());
	}

	public static Date parseDate(String dateStr, String fortmat) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(fortmat);
		return df.parse(dateStr);
	}
	
	public static String formatDate(Date date, String fortmat) {
		SimpleDateFormat df = new SimpleDateFormat(fortmat);
		return df.format(date);
	}

	public static Date getNow() {
		return new Date();
	}

	public static int compare(Date left, Date right) {
		Calendar leftCal = Calendar.getInstance();
		leftCal.setTime(left);

		Calendar rightCal = Calendar.getInstance();
		rightCal.setTime(right);

		return leftCal.compareTo(rightCal);
	}

	/**
	 * 
	 * @param date  当前时间
	 * @param field 修改时间类型 如Calendar.DAY_OF_MONTH
	 * @param i     修改时间大小
	 * @return
	 */
	public static Date add(Date date, int field, int i) {
		if (date == null) {
			return null;
		} else {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(field, i);
			return c.getTime();
		}
	}

	/**
	 * 返回两个时间差的绝对值
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static Long diff(Date start, Date end) {
		return Math.abs(start.getTime() - end.getTime());
	}

	public static int differentDaysByMillisecond(Date date1, Date date2) {
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
		return days;
	}

	public static int differentDays(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if (year1 != year2) // 同一年
		{
			int timeDistance = 0;
			for (int i = year1; i < year2; i++) {
				if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) // 闰年
				{
					timeDistance += 366;
				} else // 不是闰年
				{
					timeDistance += 365;
				}
			}

			return timeDistance + (day2 - day1);
		} else // 不同年
		{
			//System.out.println("判断day2 - day1 : " + (day2 - day1));
			return day2 - day1;
		}
	}

	public static void main(String[] args) throws ParseException {
		
		Date left=DateUtils.parseDate("2020-09-25 09:30:10", DateUtils.FORMAT_TIME);
		Date right=DateUtils.parseDate("2020-09-27 08:59:10", DateUtils.FORMAT_TIME);
		
		System.err.println(compare(right, left));

//		System.out.println(DateUtils.compare(left, right));

//		System.err.println(getNow().getTime());

//		System.err.println(add(getNow(), Calendar.HOUR_OF_DAY, 48));

//		System.err.println(diff(parseDate("2020-08-20 13:52:55", FORMAT_TIME), parseDate("2020-08-20 14:22:55", FORMAT_TIME)));
		
//		System.err.println(compare(parseDate("2020-08-20 13:52:55", FORMAT_TIME), parseDate("2020-08-20 14:22:55", FORMAT_TIME)));
//		System.err.println(compare(parseDate("2020-08-20 14:22:55", FORMAT_TIME), parseDate("2020-08-20 13:52:55", FORMAT_TIME)));
	}
}
