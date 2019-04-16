package com.JBProgramming.someGame.foundation;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class Window {

	public static JFrame w;
	private static boolean fullscreen = true;

	public Window(Main main) {
		w = new JFrame("Some Game");
		w.setUndecorated(true);
		w.setExtendedState(JFrame.MAXIMIZED_BOTH);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				main.finish();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				
			}

		});
		w.add(main);
		w.setVisible(true);
		main.start();
		setFullscreen(false);
	}

	public static boolean getFullscreen() {
		return fullscreen;
	}

	public static void setFullscreen(boolean fullscreen) {
		Window.fullscreen = fullscreen;
		w.dispose();
		w.setUndecorated(fullscreen);
		if (fullscreen)
			w.setExtendedState(JFrame.MAXIMIZED_BOTH);
		else
			w.setExtendedState(JFrame.NORMAL);
		w.setVisible(true);
	}

}
