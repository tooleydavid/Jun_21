package com.ss.jb.wk1;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.ss.jb.four.SingletonClass;


public class SampleSingleton {

	private static Connection conn = null;
	
	private static Integer lock = 0;
	
	private static SampleSingleton instance = null;
	
	
	public static SampleSingleton getInstance() {
		if(instance != null)//returns instance if already created
		{
			return instance;
		}
		synchronized(lock) {
			if(instance == null) //double checks to make sure a different thread didn't create an instance before
			{
				instance = new SampleSingleton();
			}
		}
		return instance;
	}
	
}