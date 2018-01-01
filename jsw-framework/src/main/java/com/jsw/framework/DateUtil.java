package com.jsw.framework;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static DateDifference getDateDifference(long diff) {
		DateDifference diffrence = new DateDifference();

		double diffSeconds = diff / 1000 % 60;
		double diffMinutes = diff / (60 * 1000) % 60;
		double diffHours = diff / (60 * 60 * 1000) % 24;
		double diffDays = diff / (24 * 60 * 60 * 1000);
		double totalMinInhr = diffMinutes/60;
		double totalSecInhr = diffSeconds/(60*60);
		double totalTimeInHour = (diffDays*24) + diffHours + totalMinInhr + totalSecInhr;
		
		diffrence.setSecondDifference(diffSeconds);
		diffrence.setMinuteDifference(diffMinutes);
		diffrence.setHourDifference(diffHours);
		diffrence.setDaysDifference(diffDays);
		
		diffrence.setTotalDurationInHours(totalTimeInHour);
		return diffrence;
	}

	public static DateDifference getSQLDateDifference(java.sql.Timestamp startDate, java.sql.Timestamp endDate) {
		long diff = Math.abs(endDate.getTime() - startDate.getTime());
		return getDateDifference(diff);
	}

	public static DateDifference getUtilDateDifference(java.util.Date startDate, Date endDate) {
		long diff = Math.abs(endDate.getTime() - startDate.getTime());
		return getDateDifference(diff);
	}


	
	public static void main(String[] arg) {
		utilDateDifferenceTest();
		sqlDateDifferenceTest();
	}
	
	private static void utilDateDifferenceTest() {

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
		String dateStart = "03/23/2017 11:00:00:000";
		try {
			Date d1 = format.parse(dateStart);
			Date d2 = new Date();

			DateDifference utilDiffTest = getUtilDateDifference(d1, d2);
			System.out.println("utilDiffTest => " + utilDiffTest);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	private static void sqlDateDifferenceTest() {

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
		String dateStart = "03/23/2017 11:00:00:000";
		try {
			Date d1 = format.parse(dateStart);
			Date d2 = new Date();

			java.sql.Timestamp sqlD1 = new java.sql.Timestamp(d1.getTime());
			java.sql.Timestamp sqlD2 = new java.sql.Timestamp(d2.getTime());
			
			DateDifference utilDiffTest = getSQLDateDifference(sqlD1, sqlD2);
			System.out.println("sqlDiffTest => " + utilDiffTest);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
