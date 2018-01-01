package com.jsw.framework;

public class DateDifference {
	//private long yearDifference;
	//private long monthDifference;
	private double daysDifference;
	private double hourDifference;
	private double minuteDifference;
	private double secondDifference;
	private double milisecondDifference;
	private double totalDurationInHours;
	
	//private long nanosecondDifference;
	
	/*public long getYearDifference() {
		return yearDifference;
	}
	public void setYearDifference(long yearDifference) {
		this.yearDifference = yearDifference;
	}
	public long getMonthDifference() {
		return monthDifference;
	}
	public void setMonthDifference(long monthDifference) {
		this.monthDifference = monthDifference;
	}*/
	public double getDaysDifference() {
		return daysDifference;
	}
	public void setDaysDifference(double daysDifference) {
		this.daysDifference = daysDifference;
	}
	public double getHourDifference() {
		return hourDifference;
	}
	public void setHourDifference(double hourDifference) {
		this.hourDifference = hourDifference;
	}
	public double getMinuteDifference() {
		return minuteDifference;
	}
	public void setMinuteDifference(double minuteDifference) {
		this.minuteDifference = minuteDifference;
	}
	public double getSecondDifference() {
		return secondDifference;
	}
	public void setSecondDifference(double secondDifference) {
		this.secondDifference = secondDifference;
	}
	public double getMilisecondDifference() {
		return milisecondDifference;
	}
	public void setMilisecondDifference(double milisecondDifference) {
		this.milisecondDifference = milisecondDifference;
	}
	
	/*public long getNanosecondDifference() {
		return nanosecondDifference;
	}
	public void setNanosecondDifference(long nanosecondDifference) {
		this.nanosecondDifference = nanosecondDifference;
	}*/
	
	public double getTotalDurationInHours() {
		return totalDurationInHours;
	}
	public void setTotalDurationInHours(double totalDurationInHours) {
		this.totalDurationInHours = totalDurationInHours;
	}
	
	/*public String toString() {
		String str = "yearDifference: " + yearDifference 
				+ " \nmonthDifference: " + monthDifference
				+ " \ndaysDifference: " + daysDifference
				+ " \nhourDifference: " + hourDifference
				+ " \nminuteDifference: " + minuteDifference
				+ " \nsecondDifference: " + secondDifference
				+ " \nmilisecondDifference: " + milisecondDifference
				+ " \nanosecondDifference: " + nanosecondDifference;
		return str;
	}*/
	
	public String toString() {
		String str = "\ndaysDifference: " + daysDifference
				+ " \nhourDifference: " + hourDifference
				+ " \nminuteDifference: " + minuteDifference
				+ " \nsecondDifference: " + secondDifference
				+ " \ntotalDurationInHours: " + totalDurationInHours;
		return str;
	}
	
}
