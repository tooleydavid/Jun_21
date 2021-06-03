package com.ss.jb.two;

import java.util.ArrayList;
/**
 * 
 * @author David Tooley
 * Smoothstack Day2 Assignment 1
 */
public class D2_Assignment1 {

	public static void main(String[] args) {
		int total = 0; //total numbers added together
		ArrayList<String> strs = new ArrayList<String>(); //array of all the strings
		
		for(String arg: args) {//loops through each command line arguement
			try{//tries to find an int
				total += Integer.parseInt(arg);
			}
			catch(Exception e){//catches strings and ints that are too large
				strs.add(arg);
			}
		}
		System.out.println("Sum of all numbers: "+total);//I wasn't sure if I should do something if the total becomes too large and overflows
		System.out.println("All remaining Strings: ");
		for(String str : strs)
		{
			System.out.print(str+", ");
		}
		
	}
}
