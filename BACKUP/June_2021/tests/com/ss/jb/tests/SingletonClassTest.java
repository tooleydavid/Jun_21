package com.ss.jb.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ss.jb.four.SingletonClass;

public class SingletonClassTest {

	@Test
	public void testGetInstance()
	{
		SingletonClass s1 = SingletonClass.getInstance();
		SingletonClass s2 = SingletonClass.getInstance();
		SingletonClass s3 = SingletonClass.getInstance();
		
		assertEquals(s1,s2);
		assertEquals(s1,s3);
		assertEquals(s3,s2);
		assertEquals(s3,SingletonClass.getInstance());
	}
}
