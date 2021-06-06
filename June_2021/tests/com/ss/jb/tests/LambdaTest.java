package com.ss.jb.tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.ss.jb.five.Lambda;

public class LambdaTest {
	List<String> arr = Arrays.asList("Optimistic","Everything in its Right Place","Karma Police","Exit Music","Videotape","Decks Dark","Pyramid Song","Sit Down. Stand Up");
	List<String> arr2 = Arrays.asList("Ziggy Stardust","Space Oddity","Life on Mars?","Heroes","Young Americans","Quicksand");
	
	@Test
	public void testShortToLong()
	{
		arr = Lambda.shortToLong(arr);
		assertEquals(Arrays.asList("Videotape","Optimistic","Exit Music","Decks Dark","Karma Police","Pyramid Song","Sit Down. Stand Up","Everything in its Right Place"),arr);
		
		arr2 = Lambda.shortToLong(arr2);
		assertEquals(Arrays.asList("Heroes","Quicksand","Space Oddity","Life on Mars?","Ziggy Stardust","Young Americans"),arr2);
	}
	
	@Test
	public void testLongToShort()
	{
		arr = Lambda.longToShort(arr);
		assertEquals(Arrays.asList("Everything in its Right Place","Sit Down. Stand Up","Karma Police","Pyramid Song","Optimistic","Exit Music","Decks Dark","Videotape"),arr);
	
		arr2 = Lambda.longToShort(arr2);
		assertEquals(Arrays.asList("Young Americans","Ziggy Stardust","Life on Mars?","Space Oddity","Quicksand","Heroes"),arr2);
	}
	
	@Test
	public void testAlphabetically()
	{
		arr = Lambda.alphabetically(arr);
		assertEquals(Arrays.asList("Decks Dark","Everything in its Right Place","Exit Music","Karma Police","Optimistic","Pyramid Song","Sit Down. Stand Up","Videotape"),arr);
		
		arr2 = Lambda.alphabetically(arr2);
		assertEquals(Arrays.asList("Heroes","Life on Mars?","Quicksand","Space Oddity","Young Americans","Ziggy Stardust"),arr2);
	}
	
	@Test
	public void TestContainE()
	{
		arr = Lambda.containE(arr);
		assertEquals(Arrays.asList("Everything in its Right Place","Karma Police","Exit Music","Videotape","Decks Dark","Optimistic","Pyramid Song","Sit Down. Stand Up"),arr);
		
		arr2 = Lambda.containE(arr2);
		assertEquals(Arrays.asList("Space Oddity","Life on Mars?","Heroes","Young Americans","Ziggy Stardust","Quicksand"),arr2);
	}
	
	@Test
	public void TestContainEWithHelper()
	{
		arr = Lambda.containEWithHelper(arr);
		assertEquals(Arrays.asList("Everything in its Right Place","Karma Police","Exit Music","Videotape","Decks Dark","Optimistic","Pyramid Song","Sit Down. Stand Up"),arr);
		
		arr2 = Lambda.containEWithHelper(arr2);
		assertEquals(Arrays.asList("Space Oddity","Life on Mars?","Heroes","Young Americans","Ziggy Stardust","Quicksand"),arr2);
	}
}
