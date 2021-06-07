/**
 * 
 */
package com.ss.jb.five;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author David Tooley
 *SmoothStack Day 5 Assignment 1 Part 2
 */
public class EvenOdd {

	/**
	 * Main Method
	 * @param args
	 * returns comma separated string based on a given list of integer. each element should be preceded by the letter e for even and o for odd
	 */
	static String result = "";
	public static void main(String[] args) {
		ArrayList<Integer> intarr = new ArrayList<>();
		for(int i = 0; i < 10; i++)
		{
			intarr.add((int)(Math.random()*100+1));
		}

		String result = evenOrOdd(intarr);
		System.out.println(result);
	}
	
	/**
	 * Separates the evens from the odds and then adds the extra strings
	 * @param intarr
	 */
	public static String evenOrOdd(List<Integer> intarr)
	{
		if (intarr.size()==0)
		{
			return "";
		}
		result = "";
		//Stream<Integer> intStream = intarr.stream().filter(p -> p%2==0);
		
		Map<Boolean, List<Integer>> intStream = intarr.stream().collect(Collectors.partitioningBy(p -> p%2 == 0, Collectors.toList()));
		List<Integer> odds = intStream.get(false);
		List<Integer> evens = intStream.get(true);

		//result = odds.stream().forEach((i)->"o".concat(i.toString()).collect(Collectors.toList()));
		//odds.forEach((i)->System.out.print("o"+i+","));
		//evens.forEach((i)->System.out.print("e"+i+","));
		
		odds.forEach((i)->result = result.concat("o"+i.toString()+","));
		evens.forEach((i)->result = result.concat("e"+i.toString()+","));
		result = result.substring(0,result.length()-1);
		return result;

	}

}
