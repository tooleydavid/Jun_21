package com.ss.jb.wk1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author user
 *
 */
public class Wk1_Assignment5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> input = Arrays.asList(1,2,2,2,5,5,2,2);
		groupSumClump( input, 10);
	}
	static boolean metTarget = false;
	
	public static boolean groupSumClump(List<Integer> arr,Integer target)
	{
		metTarget = false;
		List<Integer> temp = new ArrayList<>();
		Integer current;
		int j;
		int count = 0;
		for(int i = 0; i < arr.size(); i++)
		{
			j = i;
			current = arr.get(i);
			temp.add(arr.get(i));
			while(j+1 <arr.size() && arr.get(j+1) == current)
			{
				temp.set(count,temp.get(count)+current);
				j++;
			}
			i = j;
			count++;
		}
		
		for(Integer num: temp)
		{
			System.out.println(num);
		}
		metTarget = false;
		groupHelp(arr,target,arr.size()-1,0,0);
		System.out.println(metTarget);
		return metTarget;
	}
	
	
	private static void groupHelp(List<Integer> arr,Integer target,Integer size,Integer current,Integer result)
	{
		if(result == target)
		{
			metTarget = true;
			return;
		}
		if(current > size || result >= target)
		{
			return;
		}
		
		groupHelp(arr,target,size,current+1,result+arr.get(current));
		
		groupHelp(arr,target,size,current+1,result);
		return;
	}

}
