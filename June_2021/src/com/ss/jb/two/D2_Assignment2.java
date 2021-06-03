package com.ss.jb.two;

/**
 * 
 * @author David Tooley
 * Smoothstack Day2 Assignment 2
 */
public class D2_Assignment2 {
	public static void main(String[] args) {
		final int size1 = 10;
		final int size2 = 10;
		int[][] arr = new int[size1][size2];
		
		//populates the 2d array with random numbers between 1-100
		for(int i = 0; i < size1; i++)
		{
			for(int j = 0; j <size2; j++)
			{
				arr[i][j] = (int)(Math.random()*100+1);	
			}
		}
		
		int maxi = 0;
		int maxj = 0;
		int maxNum = arr[0][0];
		//loops through the array looking for the highest numbers and stores them
		for(int i = 0; i < size1; i++)
		{
			for(int j = 0; j <size2; j++)
			{
				if(arr[i][j] > maxNum)
				{
					maxi = i;
					maxj = j;
					maxNum = arr[i][j];
				}
			}
		}
		//printArr(arr,size1,size2); used for testing
		System.out.println("The largest number in the 2D array is: "+maxNum+" at index: ("+maxi+", "+maxj+")");
	}
	
	//Used to print out all of the numbers in the array for testing
    public static void printArr(int[][] arr,int size1, int size2) {
    	for(int i = 0; i < size1; i++)
		{
			for(int j = 0; j <size2; j++)
			{
				System.out.println(i+" "+j+" "+arr[i][j]);
			}
		}
    }
}
