package com.ss.jb.wk1;

@FunctionalInterface
interface FunctionalInt{
	Integer[] doMath(Integer[] arr);
}

interface FunctionalStr{
	String[] doMath(String[] arr);
}

public class Wk1_Assignment2 {
	

	public static void main(String[] args)
	{
		Integer[] arr = new Integer[] {6,8,6,8,-1};
		//FunctionalInt func = rightDigit();
		FunctionalInt func = doubling();
		Integer[] result = func.doMath(arr);
		
		for(Integer num: result)
		{
			System.out.println(num);
		}
		
		
		String[] arr2 = new String[] {"xxax", "xbxbx", "xxcx" };
		FunctionalStr func2 = noX();
		String[] result2 = func2.doMath(arr2);
		
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
			Integer[] temp = new Integer[a.length];
			for(int i = 0; i < a.length; i++)
			{
				temp[i] = a[i]%10;
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
			Integer[] temp = new Integer[a.length];
			for(int i = 0; i < a.length; i++)
			{
				temp[i] = a[i]*2;
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
			String[] temp = new String[a.length];
			String str;
			for(int i = 0; i < a.length; i++)
			{
				str ="";
				for(int j = 0; j <a[i].length(); j++)
				{
					if(a[i].charAt(j) != 'x' && a[i].charAt(j) != 'X' )
					{
						str = str.concat(""+a[i].charAt(j));
					}
				}
				temp[i] = str;
			}
			return temp;
			});
	}
}
