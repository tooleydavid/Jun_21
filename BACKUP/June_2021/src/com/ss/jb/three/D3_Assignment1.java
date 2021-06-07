/**
 * 
 */
package com.ss.jb.three;

import java.io.File;
import java.util.Scanner;

/**
 * @author David Tooley
 * SmoothStack Day 3 Assignment 1
 *
 */
public class D3_Assignment1 {

	/**
	 * Main Method
	 * gets a list of all file/directory names (including in subdirectories) under a given directory.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Please give the directory of the files/directories you want listed: ");
		Scanner scan = new Scanner(System.in);
		String fileName = scan.nextLine(); //Gets user's input
		File file = new File(fileName);
		
		
		while(!file.exists())//checks if the file exists
		{
			System.out.println("We could not find a file for the directory you gave \nplease enter a new directory or type \"quit\" to exit: ");
			fileName = scan.nextLine();//gets new user input
			if("quit".equalsIgnoreCase(fileName)) //checks if user wants to quit
			{
				scan.close();
				return;
			}
			file = new File(fileName); //updates file
		}
		
		try {
			String[] filesList = file.list(); //gets the list of files/directory
			String[] subfiles;
			File subfile = null;
			for(String filesName : filesList) //loops through list printing out the results
			{
				System.out.println(filesName);//Prints out filename
				
				
				
				if(!"/".equals(fileName) && !"\\".equals(fileName) )
				{
					subfile = new File(fileName+"\\"+filesName);//Checks if there are any subdirectories
				}
				else {
					subfile = new File(fileName+filesName);//There was an issue when using root so this is a work around
				}
				subfiles = subfile.list();
				
				try {
					for(String subfilesName: subfiles)//prints out the subdirectories
					{
						System.out.println("\t- "+subfilesName);
					}
				}catch(NullPointerException f) {

				}
			} 
			
			
			if(filesList.length==0)// checks for empty directories just to give some kind of output
			{
				System.out.println("The directory you gave is empty");
			}
		}catch(NullPointerException e) {//If the user gives a file
			System.out.println("Make sure you give a directory");
			main(args);
		}
		scan.close();
	}
}
