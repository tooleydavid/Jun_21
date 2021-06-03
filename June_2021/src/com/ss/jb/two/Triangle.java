package com.ss.jb.two;

public class Triangle implements Shape{
	private double base;
	private double height;
	
	public Triangle(double base, double height)
	{
		this.base = base;
		this.height = height;
	}
	
	@Override
	public double calculateArea() {//(b*h)/2
		return (base * height)/2;
	}

	@Override
	public void display() {
		System.out.println( calculateArea());
		
	}

}
