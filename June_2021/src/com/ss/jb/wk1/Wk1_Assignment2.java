package com.ss.jb.wk1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Wk1_Assignment2 {
	

	public static void main(String[] args)
	{
		List<Integer> arr = Arrays.asList(16, 8, 886, 8, 1);
		FunctionalInt func = rightDigit();
		//FunctionalInt func = doubling();
		List<Integer> result = func.doMath(arr);
		
		for(Integer num: result)
		{
			System.out.println(num);
		}
		
		
		List<String> arr2 = Arrays.asList("xxax", "xbxbx", "xxcx" );
		FunctionalStr func2 = noX();
		List<String> result2 = func2.doMath(arr2);
		
		for(String str: result2)
		{
			System.out.println(str);
		}
	}
	
	/**
	 * Given a list of non-negative integers, return an integer list of the rightmost digits
	 * @return
	 */
	public static FunctionalInt rightDigit() {
		return (a-> {
			List<Integer> temp = new ArrayList<>(a.size());
			for(int i = 0; i < a.size(); i++)
			{
				temp.add(a.get(i)%10);
			}
			return temp;
			});
	}
	
	
	/**
	 * Given a list of integers, return a list where each integer is multiplied by 2.
	 * @return
	 */
	public static FunctionalInt doubling() {
		return (a-> {
			List<Integer> temp = new ArrayList<>(a.size());
			for(int i = 0; i < a.size(); i++)
			{
				temp.add(a.get(i)*2);
			}
			return temp;
			});
	}
	
	
	
	/**
	 * Given a list of strings, return a list where each string has all its "x" removed.
	 * @return
	 */
	public static FunctionalStr noX() {
		return (a-> {
			List<String> temp = new ArrayList<>(a.size());
			String str;
			for(int i = 0; i < a.size(); i++)//loops through the array
			{
				str ="";
				for(int j = 0; j <a.get(i).length(); j++)//loops through each string
				{
					if(a.get(i).charAt(j) != 'x' && a.get(i).charAt(j) != 'X' )//looks for the letter x
					{
						str = str.concat(""+a.get(i).charAt(j));
					}
				}
				temp.add(str);
			}
			return temp;
			});
	}
}
