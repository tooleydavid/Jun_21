package com.ss.jb.four;

/**
 * @author David Tooley
 * SmoothStack Day 4 Assignment 2
 */
public class Deadlock {

	/**
	 * Main Method
	 * Write a program to create a deadlock between two threads.
	 * @param args
	 */
	public static void main(String[] args) {
		String str1 = "This is String 1";
		String str2 = "This is String 2";
		
		System.out.println("Program has started");
		
		Thread thread1 = new Thread(() -> {//THREAD 1
			synchronized(str1) {//tries to get str2 and then str1
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized(str2) {
					System.out.println("Thread 1 has both locks");
				}
			}
		});
		
		
		Thread thread2 = new Thread(() -> {//THREAD 2
			synchronized(str2) {//tries to get str2 and then str1
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized(str1) {
					System.out.println("Thread 2 has both locks");
				}
			}
		});
		thread1.start();//starts both threads
		thread2.start();
		
		try {
			thread1.join();//Waits for threads to end
			thread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Program Ended");
		
		
	}
}
