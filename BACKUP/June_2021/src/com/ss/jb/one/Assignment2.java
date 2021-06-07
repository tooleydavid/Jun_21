package com.ss.jb.one;

import java.util.Scanner;

/**
 * @author David Tooley
 * Smoothstack day 1 assignment 2
 */
public class Assignment2 {

	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String[] args) {
		//Gets a random number between 1-100
		int answer = (int)(Math.random()*100+1);		
		Scanner scan = new Scanner(System.in);
		//Gives user 5 attempts to guess or if they guess it
		int numGuesses = 5;
		boolean correct = false;
		while(numGuesses > 0 && !correct)
		{
			System.out.println("You have "+numGuesses+" guesses left");
			System.out.println("Please enter your guess:  ");
			//Gets user's input and stores it as an int
			int guess = Integer.parseInt(scan.nextLine());
			if(Math.abs(answer-guess)<=10)
			{
				correct = true;
			}
			else
			{
				numGuesses--;
			}
		}
		if(numGuesses == 0)
			System.out.print("Sorry,");
		else
			System.out.print("Congrats,");
		scan.close();
	}
}

