package com.JBProgramming.someGame.rendering;

import com.JBProgramming.someGame.foundation.Main;

public class Point {

	public double x, y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		long l = System.nanoTime();
		for (int i = 0; i < 1000000000; i++) {
			int j = (int) (x * Main.w());
			nothing(j);
		}
		System.out.println(System.nanoTime() - l);
		return (int) (x * Main.w());
	}

	public int getY() {
		return (int) (y * Main.h());
	}
	
	public void nothing(int i) {
		
	}

}
