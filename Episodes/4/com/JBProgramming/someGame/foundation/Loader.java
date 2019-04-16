package com.JBProgramming.someGame.foundation;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Loader {

	public static List<LoadProcess> loadProcesses = new ArrayList<LoadProcess>();

	public static void addLoadProcess(LoadProcess r) {
		try {
			boolean b = true;
			for (int i = 0; i < loadProcesses.size(); i++)
				if (r.equals(loadProcesses.get(i)))
					b = false;
			if (b)
				loadProcesses.add(r);
		} catch (Exception e) {
			addLoadProcess(r);
		}
	}

	public static void loadAll() {
		ArrayList<LoadProcess> reordered = new ArrayList<LoadProcess>(loadProcesses);
		for (int i = 0; i < reordered.size(); i++) {
			for (int j = i + 1; j < reordered.size(); j++) {
				if (reordered.get(i).priority > reordered.get(j).priority) {
					LoadProcess temp = reordered.get(i);
					reordered.add(i, reordered.get(j));
					reordered.remove(i + 1);
					reordered.add(j, temp);
					reordered.remove(j + 1);
				}
			}
		}
		for (int i = reordered.size() - 1; i >= 0; i--) {
			reordered.get(i).r.run();
			System.out.println(reordered.get(i));
		}
	}

	private static void checkDirectory(File directory, String pckgname, ArrayList<Class<?>> classes)
			throws ClassNotFoundException {
		File tmpDirectory;

		if (directory.exists() && directory.isDirectory()) {
			final String[] files = directory.list();

			for (final String file : files) {
				if (file.endsWith(".class")) {
					try {
						classes.add(Class.forName(pckgname + '.' + file.substring(0, file.length() - 6)));
					} catch (final NoClassDefFoundError e) {

					}
				} else if ((tmpDirectory = new File(directory, file)).isDirectory()) {
					checkDirectory(tmpDirectory, pckgname + "." + file, classes);
				}
			}
		}
	}

	private static void checkJarFile(JarURLConnection connection, String pckgname, ArrayList<Class<?>> classes)
			throws ClassNotFoundException, IOException {
		final JarFile jarFile = connection.getJarFile();
		final Enumeration<JarEntry> entries = jarFile.entries();
		String name;

		for (JarEntry jarEntry = null; entries.hasMoreElements()
				&& ((jarEntry = entries.nextElement()) != null);) {
			name = jarEntry.getName();

			if (name.contains(".class")) {
				name = name.substring(0, name.length() - 6).replace('/', '.');

				if (name.contains(pckgname)) {
					classes.add(Class.forName(name));
				}
			}
		}
	}

	public static List<Class<?>> getClassesForPackage(String pckgname) throws ClassNotFoundException {
		final ArrayList<Class<?>> classes = new ArrayList<Class<?>>();

		try {
			final ClassLoader cld = Thread.currentThread().getContextClassLoader();

			if (cld == null)
				throw new ClassNotFoundException("Can't get class loader.");

			final Enumeration<URL> resources = cld.getResources(pckgname.replace('.', '/'));
			URLConnection connection;

			for (URL url = null; resources.hasMoreElements() && ((url = resources.nextElement()) != null);) {
				try {
					connection = url.openConnection();

					if (connection instanceof JarURLConnection) {
						checkJarFile((JarURLConnection) connection, pckgname, classes);
					} else {
						try {
							checkDirectory(new File(URLDecoder.decode(url.getPath(), "UTF-8")), pckgname, classes);
						} catch (final UnsupportedEncodingException ex) {
							throw new ClassNotFoundException(
									pckgname + " does not appear to be a valid package (Unsupported encoding)",
									ex);
						}
					}
				} catch (final IOException ioex) {
					throw new ClassNotFoundException(
							"IOException was thrown when trying to get all resources for " + pckgname, ioex);
				}
			}
		} catch (final NullPointerException ex) {
			throw new ClassNotFoundException(
					pckgname + " does not appear to be a valid package (Null pointer exception)", ex);
		} catch (final IOException ioex) {
			throw new ClassNotFoundException(
					"IOException was thrown when trying to get all resources for " + pckgname, ioex);
		}

		return classes;
	}

	public static void loadClasses() {
		try {
			for (Class<?> c : getClassesForPackage("com.JBProgramming.someGame")) {
				c.getClassLoader().loadClass(c.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
