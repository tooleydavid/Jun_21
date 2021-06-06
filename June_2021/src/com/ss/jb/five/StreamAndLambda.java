package com.ss.jb.five;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
	 * @author David Tooley
	 *SmoothStack Day 5 Assignment 1 Part 2
	 */
public class StreamAndLambda {
		/**
		 * Main Method
		 * @param args
		 * Given a list of Strings, write a method that returns a list of all strings that start with the letter 'a' (lower case) and have exactly 3 letters
		 */
		public static void main(String[] args) {
			List<String> strs = Arrays.asList("aaa","bbb","aa","abc","ca","cbaa","dbca","acc");
			List<String> result = startsWithA(strs);
			for(String str:result)
			{
				System.out.println(str);
			}
		}
		/**
		 * filters by checking if first letter is an a 
		 * then filters by checking if the length is 3
		 * then prints out the results
		 * @param strs
		 * @return 
		 */
		public static List<String> startsWithA(List<String> strs)
		{
			if(strs.size()==0)
			{
				return strs;
			}
			List<String> result =strs.stream().filter((p)->p.charAt(0)=='a').filter((p)->p.length()==3).collect(Collectors.toList());
			return result;
		}
}
