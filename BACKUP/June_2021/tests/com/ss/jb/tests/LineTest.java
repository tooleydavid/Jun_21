/**
 * 
 */
package com.ss.jb.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ss.jb.four.Line;

/**
 * @author David Tooley
 * SmoothStack Day 4 Assignment 4
 * Create tests for the getSlope, getDistance, and parallelTo methods.
Because of rounding errors, it is bad practice to test double values for exact equality. To get around this, you can pass a small value (such as .0001) to assertEquals to be used as a delta.
 */

public class LineTest {

	Line l1 = new Line(1,2,3,4);
	Line l2 = new Line(0,0,5,10);
	Line l3 = new Line(5,5,5,5);
	Line l4 = new Line(-5,-8,4,-5);
	Line l5 = new Line(2,3,4,5);
	
	@Test
	public void testGetSlope()
	{
		assertEquals(1,l1.getSlope(),0.0001);
		assertEquals(2,l2.getSlope(),0.0001);
		assertEquals(.3333,l4.getSlope(),0.0001);
		assertEquals(1,l5.getSlope(),0.0001);
		try {//Catches the division by 0
			l3.getSlope();
			assertTrue(false);
		}catch(ArithmeticException e) {
			assertTrue(true);
		}
	}
	
	@Test(expected = ArithmeticException.class)
	public void testGetSlopeException()
	{
		l3.getSlope();
	}
	
	@Test
	public void getDistanceTest() {
		assertEquals(2.8284,l1.getDistance(),0.0001);
		assertEquals(11.1803,l2.getDistance(),0.0001);
		assertEquals(0,l3.getDistance(),0.0001);
		assertEquals(9.4868,l4.getDistance(),0.0001);
		assertEquals(2.8284,l5.getDistance(),0.0001);
		
	}
	
	@Test
	public void parallelTotest() {
		assertTrue(l1.parallelTo(l5));
		assertTrue(!l1.parallelTo(l2));
		try {//Catches the division by 0
			assertTrue(!l3.parallelTo(l4));
			assertTrue(false);
		}catch(ArithmeticException e) {
			assertTrue(true);
		}
		assertTrue(!l2.parallelTo(l5));
		assertTrue(l5.parallelTo(l1));
	}

	
}
