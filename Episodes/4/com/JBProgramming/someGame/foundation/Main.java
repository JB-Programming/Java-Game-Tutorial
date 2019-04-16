package com.JBProgramming.someGame.foundation;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.JBProgramming.someGame.rendering.Point;

public class Main extends Canvas {

	private static final long serialVersionUID = 22697724278148976L;

	public static Main m;

	public static void main(String[] args) {
		m = new Main();
	}

	private static boolean running = false;

	private static Thread thread;

	public Main() {
		new Window(this);
	}

	public void start() {
		Loader.loadClasses();
		Loader.loadAll();
		running = true;
		thread = new Thread(() -> {
			while (running) {
				tick();
				render();
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}

	public float h = 0.5F;

	private void tick() {
		h += 0.0005F;
		if (m != null)
			new Point(0.5D, 0.5D).getX();
	}

	private void render() {
		BufferStrategy buf = getBufferStrategy();
		if (buf == null) {
			createBufferStrategy(2);
			render();
			return;
		}
		Graphics g = buf.getDrawGraphics();

		g.setColor(Color.getHSBColor(h, 1F, 0.5F));
		g.fillRect(0, 0, getWidth(), getHeight());

		g.dispose();
		buf.show();
	}

	public void finish() {
		try {
			running = false;
			thread.join();
		} catch (Throwable e) {
			e.printStackTrace();
			finish();
		}
	}

	public static int w() {
		return m.getWidth();
	}

	public static int h() {
		return m.getHeight();
	}

}
