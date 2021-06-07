package com.ss.jb.tests;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import com.ss.jb.five.Dates;


public class DatesTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
	   System.setOut(new PrintStream(outContent));
	}
	@Test
	public void testLengthOfMonthsLeapYear()
	{
		String str = "2020";
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        System.setIn(bais);
		Dates.lengthOfMonths();
		assertEquals("Please enter a year: \r\n"
				+ "Year: 2020\r\n"
				+ "JANUARY     	has 31 days\r\n"
				+ "FEBRUARY     	has 29 days\r\n"
				+ "MARCH     	has 31 days\r\n"
				+ "APRIL     	has 30 days\r\n"
				+ "MAY     	has 31 days\r\n"
				+ "JUNE     	has 30 days\r\n"
				+ "JULY     	has 31 days\r\n"
				+ "AUGUST     	has 31 days\r\n"
				+ "SEPTEMBER     	has 30 days\r\n"
				+ "OCTOBER     	has 31 days\r\n"
				+ "NOVEMBER     	has 30 days\r\n"
				+ "DECEMBER     	has 31 days\r\n",outContent.toString());
	}
	
	@Test
	public void testLengthOfMonths()
	{
		String str = "2021";
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        System.setIn(bais);
		Dates.lengthOfMonths();
		assertEquals("Please enter a year: \r\n"
				+ "Year: 2021\r\n"
				+ "JANUARY     	has 31 days\r\n"
				+ "FEBRUARY     	has 28 days\r\n"
				+ "MARCH     	has 31 days\r\n"
				+ "APRIL     	has 30 days\r\n"
				+ "MAY     	has 31 days\r\n"
				+ "JUNE     	has 30 days\r\n"
				+ "JULY     	has 31 days\r\n"
				+ "AUGUST     	has 31 days\r\n"
				+ "SEPTEMBER     	has 30 days\r\n"
				+ "OCTOBER     	has 31 days\r\n"
				+ "NOVEMBER     	has 30 days\r\n"
				+ "DECEMBER     	has 31 days\r\n"
				+ "",outContent.toString());
	}
	
	@Test 
	public void testListAllMondays()
	{
		String str = "MAY";
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        System.setIn(bais);
		Dates.listAllMondays();
		assertEquals("Please enter a month such as MARCH,JANUARY, or JULY: \r\n"
				+ "2021-05-03\r\n"
				+ "2021-05-10\r\n"
				+ "2021-05-17\r\n"
				+ "2021-05-24\r\n"
				+ "2021-05-31\r\n"
				+ "",outContent.toString());
	}
	
	@Test
	public void testisNotFriday13th()
	{
		String str = "2020\nJULY\n13";
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        System.setIn(bais);		
		Dates.isFriday13th();
		assertEquals("Please enter a year: \r\n"
				+ "Please enter a month such as MARCH,JANUARY, or JULY:\r\n"
				+ "Please enter a day in numerical format such as 1, 14, or 31:\r\n"
				+ "The given date is not Friday the 13th\r\n",outContent.toString());
	}
	
	@Test
	public void testisFriday13th()
	{
		String str = "2020\nNovember\n13";
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        System.setIn(bais);		
		Dates.isFriday13th();
		assertEquals("Please enter a year: \r\n"
				+ "Please enter a month such as MARCH,JANUARY, or JULY:\r\n"
				+ "Please enter a day in numerical format such as 1, 14, or 31:\r\n"
				+ "NOVEMBER 13 2020 is Friday the 13th\r\n",outContent.toString());
	}
}
