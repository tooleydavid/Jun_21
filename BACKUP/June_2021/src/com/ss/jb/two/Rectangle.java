package com.ss.jb.two;

public class Rectangle implements Shape {

	private double height;
	private double width;
	
	public Rectangle(double width, double height)
	{
		this.width = width;
		this.height = height;
	}
	
	@Override
	public double calculateArea() {//L*W
		return width * height;
	}

	@Override
	public void display() {
		System.out.println( calculateArea());
		
	}

}
