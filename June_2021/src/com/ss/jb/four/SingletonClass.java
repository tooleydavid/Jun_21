package com.ss.jb.four;

/**
 * @author David Tooley
 * SmoothStack Day 4 Assignment 1
 * implement a Singleton with double checked locking.
 */
public class SingletonClass {
	
	private static SingletonClass singletonInstance = null;
	private static Integer lock = 0;
	
	private SingletonClass() {
		
	}
	
	public static SingletonClass getInstance() {
		if(singletonInstance != null)//returns instance if already created
		{
			return singletonInstance;
		}
		synchronized(lock) {
			if(singletonInstance == null) //double checks to make sure a different thread didn't create an instance before
			{
				singletonInstance = new SingletonClass();
			}
		}
		return singletonInstance;
	}
	
	
	
	
}
