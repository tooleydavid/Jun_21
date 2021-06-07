package com.ss.jb.tests;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.ss.jb.five.EvenOdd;

public class EvenOddTest {

	@Test
	public void testEvenOrOdd()
	{
		List<Integer> intarr = Arrays.asList(3,44);
		assertTrue("o3,e44".equals(EvenOdd.evenOrOdd(intarr)));
		
		intarr = Arrays.asList(6,89,234,5,35,2);
		assertTrue("o89,o5,o35,e6,e234,e2".equals(EvenOdd.evenOrOdd(intarr)));
		
		intarr = Arrays.asList(6,2,8);
		assertTrue("e6,e2,e8".equals(EvenOdd.evenOrOdd(intarr)));
		
		intarr = Arrays.asList(9,7,15);
		assertTrue("o9,o7,o15".equals(EvenOdd.evenOrOdd(intarr)));
		
		intarr = Arrays.asList();
		assertTrue("".equals(EvenOdd.evenOrOdd(intarr)));
	}
	
}
