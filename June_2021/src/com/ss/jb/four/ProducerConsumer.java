package com.ss.jb.four;

/**
 * @author David Tooley
 * SmoothStack day 4 Assignment 3
 */
public class ProducerConsumer {

	public static Integer[] buffer;
	static Integer size = 5;
	static Integer currentSize = 0;
	/**
	 * Main Method
	 * Write a program with one thread (the producer) that produces items (in this case, simple ints). Another thread (the consumer) consumes items. 
	 * For communacation purposes, both threads have access to a bounded buffer which is basically an array.
	 * @param args
	 */
	public static void main(String[] args) {
		buffer = new Integer[size];
		for(int i = 0; i < size; i++)
		{
			buffer[i] = null;
		}
		System.out.println(buffer.length);
		Producer producer = new Producer();
		//Producer producer2 = new Producer();
		//Producer producer3 = new Producer();
		//Producer producer4 = new Producer();
		Consumer consumer = new Consumer();
		Consumer consumer2 = new Consumer();
		Consumer consumer3 = new Consumer();
		Consumer consumer4 = new Consumer();
		
		producer.start();
		//producer2.start();
		//producer3.start();
		//producer4.start();
		consumer.start();
		consumer2.start();
		consumer3.start();
		consumer4.start();
	}
	
	/**
	 * 
	 * @author David Tooley
	 * Tries to access buffer and adds a value if its not full
	 */
	public static class Producer extends Thread{
		 private Integer count = 0;
		 
		 public void produce() {
			 synchronized(buffer) {
				 while(currentSize == size)//Checks to see if buffer is full
				 {
					try {
						buffer.wait();
					} catch (InterruptedException e) {
						System.out.println("ERROR waiting in Producer");
					}
				 }
				 
					 
					 for(int i = 0; i < size; i++)
					{
						if(buffer[i] == null)//Finds first empty spot in the array
						{
							buffer[i] = count;
							count++;
							currentSize++;
							break;
						}
					}
					 printBuffer();
					 buffer.notify();
					 try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				 
			 }
		 }
		 public void run()
		 {
			 while(true) {//Produces forever

				 produce();
			 }
		 }
	}
	
	
	/**
	 * 
	 * @author David Tooley
	 * Tries to access buffer and removes a value if not empty
	 */
	public static class Consumer extends Thread{
		
		public void consume() {
			synchronized(buffer) {
				while(currentSize == 0)//checks to see if buffer is empty
				{
					try {
						buffer.wait();
					} catch (InterruptedException e) {
						System.out.println("ERROR waiting in Consumer");
					}
				}
				
				for(int i = size-1; i >= 0; i--)//loops backwards through the buffer to find first value
				{
					if(buffer[i] != null)//Finds first empty spot in the array
					{
						buffer[i] = null;
						currentSize--;
						break;
					}
				}
				printBuffer();
				buffer.notify();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		public void run()
		{
			while(true) {//Consumes forever

				 consume();
			}
		}
	}
	
	/**
	 * Loops through the buffer and prints all the values
	 */
	public static void printBuffer() {
		System.out.print("[");
		for(Integer value:buffer)
		{
			System.out.print(value+", ");
		}
		System.out.print("]\n");
	}
}
