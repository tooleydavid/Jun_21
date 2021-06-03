package com.ss.jb.one;

/**
 * @author David Tooley
 * SmoothStack day 1 Assignment 1
 */
public class Assignment1 {

	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String[] args) {
		//FIRST PATTERN
		System.out.println("1)");
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < i+1; j++)
			{
				System.out.print("*");
			}
			System.out.print("\n");
		}
		printDots(9);
		
		
		//PATTERN 2
		System.out.println("2)");
		printDots(10);
		for(int i = 4; i > 0; i--)
		{
			for(int j = i; j > 0; j--)
			{
				System.out.print("*");
			}
			System.out.print("\n");
		}
		
		
		//PATTERN 3
		System.out.println("3)");
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 5-i; j++)
			{
				System.out.print(" ");
			}
			for(int k = 0; k < 1+2*i;k++)
			{
				System.out.print("*");
			}
			System.out.print("\n");	
		}
		printDots(11);
		
		//PATTERN 4
		System.out.println("4)");
		printDots(12);
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 2+i; j++)
			{
				System.out.print(" ");
			}
			for(int k = 0; k < 7-2*i;k++)
			{
				System.out.print("*");
			}
			System.out.print("\n");	
		}
		
	}
	
	/**
	 * Prints the correct number of dots given
	 * @param numDots : number of dots
	 */
	public static void printDots(Integer numDots) {
		for(int i = 0; i < numDots; i++)
		{
			System.out.print(".");
		}
		System.out.print("\n");
		
	}

}
