package com.ss.jb.tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.ss.jb.five.StreamAndLambda;

public class StreamAndLambdaTest {
	
	
	@Test
	public void testStartsWithA()
	{
		List<String> strs = Arrays.asList("aaa","bbb","aaaa","abc","bbc","cba","dbca","acc");
		List<String> result = StreamAndLambda.startsWithA(strs);
		assertEquals(result,Arrays.asList("aaa","abc","acc"));
		
		strs = Arrays.asList("aaaa","bbb","aa","abca","ca","cbaa","dbca","acca");
		result = StreamAndLambda.startsWithA(strs);
		assertEquals(result,Arrays.asList());
		
		strs = Arrays.asList();
		result = StreamAndLambda.startsWithA(strs);
		assertEquals(result,Arrays.asList());
	}
	
}
