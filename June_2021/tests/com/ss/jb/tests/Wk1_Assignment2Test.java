package com.ss.jb.tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.ss.jb.wk1.FunctionalInt;
import com.ss.jb.wk1.FunctionalStr;
import com.ss.jb.wk1.Wk1_Assignment2;


public class Wk1_Assignment2Test {

	@Test
	public void testRightDigit() {
		List<Integer> arr = Arrays.asList(16, 8, 886, 8, 1);
		List<Integer> arr2 = Arrays.asList(6,8,6,8,1);
		FunctionalInt func = Wk1_Assignment2.rightDigit();
		List<Integer> result = func.doMath(arr);
		assertEquals(result,arr2);
		
		
		arr = Arrays.asList(1,22,93);
		arr2 = Arrays.asList(1,2,3);
		result = func.doMath(arr);
		assertEquals(result,arr2);
		
		
		arr = Arrays.asList(10,0);
		arr2 = Arrays.asList(0,0);
		result = func.doMath(arr);
		assertEquals(result,arr2);
		
		
		arr = Arrays.asList();
		arr2 = Arrays.asList();
		result = func.doMath(arr);
		assertEquals(result,arr2);
	}
	
	@Test
	public void testDoubling() {
		List<Integer> arr = Arrays.asList(1,2,3);
		List<Integer> arr2 = Arrays.asList(2,4,6);
		FunctionalInt func = Wk1_Assignment2.doubling();
		List<Integer> result = func.doMath(arr);
		assertEquals(result,arr2);
		
		
		arr = Arrays.asList(6,8,6,8,-1);
		arr2 = Arrays.asList(12,16,12,16,-2);
		result = func.doMath(arr);
		assertEquals(result,arr2);
		
		
		arr = Arrays.asList();
		arr2 = Arrays.asList();
		result = func.doMath(arr);
		assertEquals(result,arr2);
	}
	
	@Test
	public void testNoX() {
		List<String> arr = Arrays.asList("ax","bb","cx");
		List<String> arr2 = Arrays.asList("a","bb","c");
		FunctionalStr func = Wk1_Assignment2.noX();
		List<String> result = func.doMath(arr);
		assertEquals(result,arr2);
		
		
		arr = Arrays.asList("xxax","xbxb","xxcxxxxx");
		arr2 = Arrays.asList("a","bb","c");
		result = func.doMath(arr);
		assertEquals(result,arr2);
		
		
		arr = Arrays.asList("xxx","xx","xxxXXxx");
		arr2 = Arrays.asList("","","");
		result = func.doMath(arr);
		assertEquals(result,arr2);
		
		
		arr = Arrays.asList("");
		arr2 = Arrays.asList("");
		result = func.doMath(arr);
		assertEquals(result,arr2);
	}
	
}
