package com.ss.jb.five;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author David Tooley
 * SmoothStack Day 5 Assignment 1
 */
public class Lambda {

	/**
	 * Main Method
	 * @param args
	 * lambda Sorting
	 */
	public static void main(String[] args) {
		List<String> arr = Arrays.asList("Optimistic","Everything in its Right Place","Karma Police","Exit Music","Videotape","Decks Dark","Pyramid Song","Sit Down. Stand Up");
		
		//short to long
		System.out.println("Shortest to longest: ");
		arr = shortToLong(arr);
		for(String str:arr) {
			System.out.println(str);
		}
		
		//long to short
		arr = Arrays.asList("Optimistic","Everything in its Right Place","Karma Police","Exit Music","Videotape","Decks Dark","Pyramid Song","Sit Down. Stand Up");
		System.out.println("\nLongest to shortest: ");
		arr = longToShort(arr);
		for(String str:arr) {
			System.out.println(str);
		}
		
		//alphabetically
		arr = Arrays.asList("Optimistic","Everything in its Right Place","Karma Police","Exit Music","Videotape","Decks Dark","Pyramid Song","Sit Down. Stand Up");
		System.out.println("\nalphabetically: ");
		arr = alphabetically(arr);
		for(String str:arr) {
			System.out.println(str);
		}
		
		//contain e
		arr = Arrays.asList("Optimistic","Everything in its Right Place","Karma Police","Exit Music","Videotape","Decks Dark","Pyramid Song","Sit Down. Stand Up");
		System.out.println("\nContains e > doesn't contain e: ");
		arr = containE(arr);
		for(String str:arr) {
			System.out.println(str);
		}

		//contains e with static helper method
		arr = Arrays.asList("Optimistic","Everything in its Right Place","Karma Police","Exit Music","Videotape","Decks Dark","Pyramid Song","Sit Down. Stand Up");
		System.out.println("\nContains e > doesn't contain e with helper method: ");
		arr = containEWithHelper(arr);
		for(String str:arr) {
			System.out.println(str);
		}
		
	}
	public static List<String> containEWithHelper(List<String> arr)
	{
		arr.sort(Comparator.comparingInt(str1 -> Lambda.helperMethod(str1)));
		return arr;
	}
	
	
	public static int helperMethod(String str1) {
		if(str1.contains("e")||str1.contains("E"))
			return 0;
		else 
			return 1;
	}
	
	public static List<String> shortToLong(List<String> arr)
	{
		arr.sort((String str1, String str2) -> str1.length() - str2.length());
		return arr;
	}
	
	public static List<String> longToShort(List<String> arr)
	{
		arr.sort((String str1, String str2) -> str2.length()-  str1.length());
		return arr;
	}
	
	public static List<String> alphabetically(List<String> arr)
	{
		arr.sort((String str1,String str2) -> str1.charAt(0)-str2.charAt(0));
		return arr;
	}
	
	public static List<String> containE(List<String> arr)
	{
		arr.sort(Comparator.comparingInt(str1 -> {
		if(str1.contains("e")||str1.contains("E"))
			return 0;
		else 
			return 1;
		}));
		return arr;
	}

}
