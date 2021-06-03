/**
 * 
 */
package com.ss.jb.three;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

/**
 * @author David Tooley
 * Smoothstack Day 3 Assignment 2
 *
 */
public class D3_Assignment2 {

	/**
	 * Main Method
	 * Write a Java program to append text to an existing file.
	 * C:\Users\ user\Desktop\ dtooley-swe432\coding\assignment_test.txt
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Please give the name of an existing text file you want append text to: ");
		Scanner scan = new Scanner(System.in);
		String fileName = scan.nextLine(); //Gets user's input
		fileName = checkFileName(fileName,scan); //checks to make sure it is a txt file
		File file = new File(fileName);
		
		
		while(!file.exists())//checks if the file exists
		{
			System.out.println("We could not find a text file for the file you gave \nplease enter a new text file or type \"quit\" to exit: ");
			fileName = scan.nextLine();//gets new user input
			
			if("quit".equalsIgnoreCase(fileName)) //checks if user wants to quit
			{
				scan.close();
				return;
			}
			fileName = checkFileName(fileName,scan);
			file = new File(fileName); //updates file
		}
		
		//Gets user's input for the text they want to append
		System.out.println("What would you like to append to the file: ");
		String appendText = scan.nextLine();//gets user input
		
		
		try {//Tries to append the user's text to the file
			Files.write(Paths.get(fileName), appendText.getBytes(), StandardOpenOption.APPEND);
		}catch(IOException e) { //an error occurred
			System.out.println("Something went wrong writing to the file");
			scan.close();
			main(args);
		}
		
		scan.close();
	}
	
	/**
	 * Checks to make sure the file name ends in .txt
	 * @param fileName
	 * @param scan
	 * @return returns the new or unchanged filename
	 */
	public static String checkFileName(String fileName, Scanner scan)
	{
		while(fileName.length()<4 || !".txt".equals(fileName.substring(fileName.length()-4,fileName.length())))
		{
			
			System.out.println("Please enter a file that ends in \".txt\": ");
			fileName = scan.nextLine();//gets new user input
			
		}
		return fileName;
	}

}


