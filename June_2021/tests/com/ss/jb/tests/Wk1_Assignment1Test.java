package com.ss.jb.tests;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import com.ss.jb.wk1.Wk1_Assignment1;

public class Wk1_Assignment1Test {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}
	
	@Test
	public void testLambdas()
	{
		String str = "6\n1 4\n2 5\n3 78987\n1 3\n2 12\n3 789887";
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        System.setIn(bais);
        
        Wk1_Assignment1.doLambdas();
        assertEquals("EVEN\r\n"
				+ "PRIME\r\n"
				+ "PALINDROME\r\n"
				+ "ODD\r\n"
				+ "COMPOSITE\r\n"
				+ "NOT PALINDROME\r\n"
				,outContent.toString());
	}
	@Test
	public void testLambdasEmpty()
	{
		String str = "0";
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        System.setIn(bais);
        
        Wk1_Assignment1.doLambdas();
        assertEquals(""
				,outContent.toString());
	}
}
