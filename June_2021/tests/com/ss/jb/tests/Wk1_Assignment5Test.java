package com.ss.jb.tests;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.ss.jb.wk1.Wk1_Assignment5;

public class Wk1_Assignment5Test {
	
	@Test
	public void testGroupSumClump()
	{
		List<Integer> input = Arrays.asList(1,2,2,2,5,5,2,2);
		assertTrue(Wk1_Assignment5.groupSumClump( input, 10));
		
		input = Arrays.asList(2,4,8);
		assertTrue(Wk1_Assignment5.groupSumClump( input, 10));
		
		input = Arrays.asList(1,2,4,8,1);
		assertTrue(Wk1_Assignment5.groupSumClump( input, 14));
		
		input = Arrays.asList(2,4,4,8);
		assertTrue(!(Wk1_Assignment5.groupSumClump( input, 14)));
		
		input = Arrays.asList(5);
		assertTrue(Wk1_Assignment5.groupSumClump( input, 5));
		
		input = Arrays.asList(5,5);
		assertTrue(!(Wk1_Assignment5.groupSumClump( input, 5)));
		
		input = Arrays.asList();
		assertTrue(!(Wk1_Assignment5.groupSumClump( input, 14)));
	}
}
