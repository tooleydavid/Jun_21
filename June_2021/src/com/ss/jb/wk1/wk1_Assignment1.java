package com.ss.jb.wk1;

import java.util.Scanner;

public class wk1_Assignment1 {

	public static void main(String[] args)
	{
		PerformOperation perfop;
		Scanner scan = new Scanner(System.in);
		int numQuestions = Integer.parseInt(scan.nextLine());
		
		
		for(int i = 0; i < numQuestions; i++){
			String input = scan.nextLine();
			int lambda = Integer.parseInt(input.substring(0,1));
			int num = Integer.parseInt(input.substring(2,input.length()));
			boolean result;
			
			if(lambda == 1)
			{
				perfop = isOdd();
				result = perfop.doMath(num);
				if(result)
					System.out.println("ODD");
				else
					System.out.println("EVEN");
			}
			else if(lambda == 2)
			{
				perfop = isPrime();
				result = perfop.doMath(num);
				if(result)
					System.out.println("PRIME");
				else
					System.out.println("COMPOSITE");
			}
			else
			{
				perfop = isPalindrome();
				result = perfop.doMath(num);
				if(result)
					System.out.println("PALINDROME");
				else
					System.out.println("NOT PALINDROME");
			}
			
		}	
		scan.close();
		
	}
	
	/**
	 * The lambda expression must return  if a number is odd or  if it is even.
	 * @return
	 */
	public static PerformOperation isOdd() {
		return (a-> {
			if(a % 2 == 0)
				return false;
			else 
				return true;
			});
	}
	
	/**
	 * The lambda expression must return  if a number is prime or  if it is composite.
	 * @return
	 */
	public static PerformOperation isPrime() {
		return (a-> {
				for(int i = 2; i < a/2+1; i++)
				{
					if(a % i == 0)
						return false;
				}
				return true;
			});
	}
	
	
	/**
	 * The lambda expression must return  if a number is a palindrome or  if it is not.
	 * @return
	 */
	public static PerformOperation isPalindrome() {
		return (a-> {
			for(int i = 0; i < a.toString().length()/2+1; i++)
			{
				if(a.toString().charAt(i) != a.toString().charAt(a.toString().length()-i-1))
				{
					return false;
				}
			}
			return true;
	});
	
	}	
}
