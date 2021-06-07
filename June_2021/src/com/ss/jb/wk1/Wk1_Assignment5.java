package com.ss.jb.wk1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author David Tooley
 * SmoothStack Week 1 Assignment 5
 */
public class Wk1_Assignment5 {

	/**
	 * Given an array of ints, is it possible to choose a group of some of the ints, such that the group sums to the given target, with this additional constraint:
	 *  if there are numbers in the array that are adjacent and the identical value, they must either all be chosen, or none of them chosen
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> input = Arrays.asList(2,4,4,8);
		groupSumClump( input, 10);
	}
	static boolean metTarget = false; //becomes true when the target is met
	
	public static boolean groupSumClump(List<Integer> arr,Integer target)
	{
		metTarget = false;
		List<Integer> temp = new ArrayList<>();
		Integer current;
		int j;
		int count = 0;
		for(int i = 0; i < arr.size(); i++)//adds up all adjacent numbers
		{
			j = i;
			current = arr.get(i);
			temp.add(arr.get(i));
			while(j+1 <arr.size() && arr.get(j+1) == current)//checks to see if adjacent numbers are the same number
			{
				temp.set(count,temp.get(count)+current);
				j++;
			}
			i = j;
			count++;
		}
		
		metTarget = false;
		groupHelp(temp,target,temp.size()-1,0,0);
		System.out.println(metTarget);
		return metTarget;
	}
	
	/**
	 * Recursively tries every combination of number
	 * @param arr
	 * @param target
	 * @param size
	 * @param current
	 * @param result
	 */
	private static void groupHelp(List<Integer> arr,Integer target,Integer size,Integer current,Integer result)
	{
		if(result == target)//if target is met
		{
			metTarget = true;
			return;
		}
		if(current > size || result >= target)//if greater than target or out of numbers to choose
		{
			return;
		}
		
		groupHelp(arr,target,size,current+1,result+arr.get(current)); //recursive with current number
		
		groupHelp(arr,target,size,current+1,result); //recursive without current number
		return;
	}

}
