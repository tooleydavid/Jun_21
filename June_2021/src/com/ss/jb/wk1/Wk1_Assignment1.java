package com.ss.jb.wk1;

import java.util.Scanner;

public class Wk1_Assignment1 {

	public static void main(String[] args)
	{
		doLambdas();
		
	}
	public static void doLambdas()
	{
		PerformOperation perfop;
		int numQuestions = 0;
		Scanner scan = new Scanner(System.in);
		try {
			numQuestions = Integer.parseInt(scan.nextLine());//gets the number of inputs to come
		}
		catch(NumberFormatException e)
		{
			System.out.println("ERROR: Please enter one integer");
			scan.close();
			return;
		}
		
		int lambda = 0;
		int num =0;
		for(int i = 0; i < numQuestions; i++){ //loops through inputs
			String input = scan.nextLine();
			try {
				lambda = Integer.parseInt(input.substring(0,1));//gets the first number
				num = Integer.parseInt(input.substring(2,input.length()));//gets the 2nd number
			}catch(NumberFormatException e) {
				System.out.println(lambda+"ERROR: Please only enter integers"+num);
				scan.close();
				return;
			}
			boolean result;
			
			if(lambda == 1) //is odd
			{
				perfop = isOdd();
				result = perfop.doMath(num); 
				if(result)
					System.out.println("ODD");
				else
					System.out.println("EVEN");
			}
			else if(lambda == 2) //is prime
			{
				perfop = isPrime();
				result = perfop.doMath(num);
				if(result)
					System.out.println("PRIME");
				else
					System.out.println("COMPOSITE");
			}
			else //is palindrome
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
				for(int i = 2; i < a/2+1; i++) //tries all combinations of inputs to find a factor
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
			for(int i = 0; i < a.toString().length()/2+1; i++)//loops through the string checking the first and last position and then both move in
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
