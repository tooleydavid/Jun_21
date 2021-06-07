package com.ss.jb.two;

/**
 * @author David Tooley
 *Smoothstack Day 2 Assigment 3
 */
public class ShapesMain {

	public static void main(String[] args) {
		//creates shapes
		Circle circle = new Circle(3);
		Triangle triangle = new Triangle(4,5);
		Rectangle rectangle = new Rectangle(5,7);
		
		//Rectangle
		System.out.println("Rectangle area: ");
		rectangle.display();
		
		//Circle
		System.out.println("Circle area: ");
		circle.display();
		
		//Triangle
		System.out.println("Triangle area: ");
		triangle.display();
		

	}

}
