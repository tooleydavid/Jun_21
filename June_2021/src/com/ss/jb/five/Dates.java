/**
 * 
 */
package com.ss.jb.five;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.Scanner;

/**
 * @author David Tooley
 * SmoothStack Date and Time questions
 */
public class Dates {

	/**
	 * Main Method
	 * @param args
	 * for a given year, reports the length of each month within that year.
	 */
	public static void main(String[] args) {
		//lengthOfMonths();
		//listAllMondays();
		isFriday13th();
	}
	
	/**
	 *  for a given year, reports the length of each month within that year.
	 */
	public static void lengthOfMonths()
	{
		System.out.println("Please enter a year: "); //Gets year from user
		Scanner scan = new Scanner(System.in);
		int year;
		try {
			year = Integer.parseInt(scan.nextLine());
		}
		catch(NumberFormatException e){//makes sure the year is an int
			System.out.println("Please enter a valid Year");
			scan.close();
			return;
		}
		System.out.println("Year: "+year);
		YearMonth ym;
		for(Month month: Month.values())//loops through all 12 months
		{
			ym = YearMonth.of(year,month);
			System.out.println(month+"     \thas "+ym.lengthOfMonth()+" days");//gets length of the month for the given year
			
		}
		scan.close();
	}

	/**
	 * for a given month of the current year, lists all of the mondays in that month
	 */
	public static void listAllMondays()
	{
		System.out.println("Please enter a month such as MARCH,JANUARY, or JULY: "); //gets the month from the user
		Scanner scan = new Scanner(System.in);
		Month month = Month.JANUARY;
		try {
			month = Month.valueOf(scan.nextLine().toUpperCase());//makes sure that the month name is in the proper format
		}catch(IllegalArgumentException e) {
			System.out.println("Please enter a valid month");
			scan.close();
			return;
		}
		
		YearMonth ym = YearMonth.of(Integer.parseInt(Year.now().toString()), month);//gets the current year
		
		int daycount = 1;
		while(daycount <= ym.lengthOfMonth()) //loops through all the days in the month
		{
			LocalDate day = ym.atDay(daycount);
			if(DayOfWeek.MONDAY == day.getDayOfWeek()) //checks if every day is a month day
			{
				System.out.println(day);
				daycount += 6; //adds a week to find the next monday (only 6 due to the ++ below)
			}
			daycount++;
		}
		scan.close();
	}
	
	/**
	 * tests whether a given date occurs on Friday the 13th
	 */
	public static boolean isFriday13th()
	{
		System.out.println("Please enter a year: "); //Gets Year from user
		Scanner scan = new Scanner(System.in);
		int year;
		try {
			year = Integer.parseInt(scan.nextLine());
		}
		catch(NumberFormatException e){ //Makes sure it is an int
			System.out.println("Please enter a valid Year");
			scan.close();
			return false;
		}
		
		System.out.println("Please enter a month such as MARCH,JANUARY, or JULY:"); //gets Month from User
		Month month = Month.JANUARY;
		try {
			month = Month.valueOf(scan.nextLine().toUpperCase()); //Makes sure it is in the right format
		}catch(IllegalArgumentException e) {
			System.out.println("Please enter a valid month");
			scan.close();
			return false;
		}
		
		System.out.println("Please enter a day in numerical format such as 1, 14, or 31:"); //gets day from user
		int day;
		String str = "";
		try {
			str =scan.nextLine();
			day = Integer.parseInt(str);
		}
		catch(NumberFormatException e){ //makes sure it is an int
			System.out.println("Please enter a valid Day");
			scan.close();
			return false;
		}
		if (day!=13) //makes sure the day is 13th
		{
			System.out.println("The given date is not Friday the 13th");
			scan.close();
			return false;
		}
		
		YearMonth ym = YearMonth.of(year, month);
		LocalDate dayCheck = ym.atDay(day);
		if(DayOfWeek.FRIDAY == dayCheck.getDayOfWeek()) //checks if that date is a friday
		{
			System.out.println(month+" "+day+" "+year+" is Friday the 13th");
			scan.close();
			return true;
		}
		System.out.println("The given date is not Friday the 13th");
		scan.close();
		return false;
	}
	
}
