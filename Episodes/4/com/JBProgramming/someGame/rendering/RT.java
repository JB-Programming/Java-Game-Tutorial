package com.JBProgramming.someGame.rendering;

import java.awt.Graphics2D;
import java.awt.Image;

import com.JBProgramming.someGame.foundation.Main;

public interface RT {

	public void render(RO ro, Graphics2D g);

	public static RT rectangle = new RT() {

		@Override
		public void render(RO ro, Graphics2D g) {
			if ((boolean) ro.modifiers.get("fill"))
				g.fillRect(ro.x, ro.y, ro.width, ro.height);
			else
				g.drawRect(ro.x, ro.y, ro.width, ro.height);
		}

	};
	

	public static RT line = new RT() {

		@Override
		public void render(RO ro, Graphics2D g) {
			g.drawLine(ro.x, ro.y, ro.x + ro.width, ro.y + ro.height);
		}

	};

	public static RT oval = new RT() {

		@Override
		public void render(RO ro, Graphics2D g) {
			if ((boolean) ro.modifiers.get("fill"))
				g.fillOval(ro.x, ro.y, ro.width, ro.height);
			else
				g.drawOval(ro.x, ro.y, ro.width, ro.height);
		}
		
	};
	
	public static RT image = new RT() {

		@Override
		public void render(RO ro, Graphics2D g) {
			Image i = (Image) ro.modifiers.get("image");
			g.drawImage(i, ro.x, ro.y, ro.x + ro.width, ro.y + ro.height, Main.m);
		}
		
	};
	
	public static RT warpedImage = new RT() {

		@Override
		public void render(RO ro, Graphics2D g) {
			Image i = (Image) ro.modifiers.get("image");
			Point p1 = (Point) ro.modifiers.get("p1");
			Point p2 = (Point) ro.modifiers.get("p2");
			g.drawImage(i, ro.x, ro.y, ro.x + ro.width, ro.y + ro.height,  Main.m);
		}
		
	};

}
