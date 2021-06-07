/**
 * 
 */
package com.ss.jb.three;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author David Tooley
 * SmoothStack Day 3 Assignment 3
 *
 */
public class D3_Assignment3 {

	/**
	 * Write a Java program that counts the number of times a particular character, such as 'e', appears in a file. 
	 * @param args  - The character can be specified at the command line.
	 */
	public static void main(String[] args) {
		int count = 0;
		
		//checks to make sure the user added command line arguments
		if(args.length == 0)
		{
			System.out.println("ERROR: Make sure you add what character you want to count in the command line");
			return;
		}
		
		char countChar = args[0].charAt(0);
		
		
		System.out.println("Please give the name of an existing text file you want to count the number of: ");
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
		BufferedReader bufRead;
		try {
			bufRead = new BufferedReader(new FileReader(file));//reads the file
			String text = bufRead.readLine();
			while(text != null)//Loops until EOF
			{
				for(int i = 0; i < text.length();i++ )
				{
					if(text.charAt(i) == countChar || text.charAt(i) == Character.toUpperCase('U'))//checks the lines character by character to try to find a match
					{
						count++;
					}
				}
				text = bufRead.readLine();
			}
			bufRead.close();
		} catch (FileNotFoundException e) {//Error opening
			System.out.println("There was an Error opening up the file");
			scan.close();
			return;
		}catch (IOException f) {//error reading
			System.out.println("There was an Error reading from the file");
			scan.close();
			return;
		}
		System.out.println("Total number of "+countChar+"'s: "+count);
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
