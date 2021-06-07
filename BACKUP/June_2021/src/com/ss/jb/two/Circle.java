package com.ss.jb.two;

public class Circle implements Shape {

	private double radius;
	
	public Circle(double radius)
	{
		this.radius = radius;
	}
	
	@Override
	public double calculateArea() {//pi*r^2
		return Math.PI*Math.pow(radius, 2);
	}

	@Override
	public void display() {
		System.out.println( calculateArea());
		
	}
}
