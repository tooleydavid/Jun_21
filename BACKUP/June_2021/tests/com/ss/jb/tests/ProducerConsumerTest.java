package com.ss.jb.tests;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import com.ss.jb.four.ProducerConsumer;

public class ProducerConsumerTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}
	
	@Test
	public void testPrintBuffer()
	{
		ProducerConsumer.buffer = new Integer[5];
		for(int i = 0; i < 5; i++)
		{
			ProducerConsumer.buffer[i] = null;
		}
		ProducerConsumer.printBuffer();
		assertEquals("[null, null, null, null, null, ]\n",outContent.toString());
		
	}
	
	@Test
	public void testPrintBuffer2()
	{
		ProducerConsumer.buffer = new Integer[5];
		for(int i = 0; i < 5; i++)
		{
			ProducerConsumer.buffer[i] = i;
		}
		ProducerConsumer.printBuffer();
		assertEquals("[0, 1, 2, 3, 4, ]\n",outContent.toString());
	}
	
}
