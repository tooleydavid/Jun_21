package com.ss.jb.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ss.jb.wk1.SampleSingleton;

public class SampleSingletonTest {

	@Test
	public void testGetInstance()
	{
		SampleSingleton s1 = SampleSingleton.getInstance();
		SampleSingleton s2 = SampleSingleton.getInstance();
		SampleSingleton s3 = SampleSingleton.getInstance();
		
		assertEquals(s1,s2);
		assertEquals(s1,s3);
		assertEquals(s3,s2);
		assertEquals(s3,SampleSingleton.getInstance());
	}
}
