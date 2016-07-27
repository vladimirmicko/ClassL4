package com.demo.vlada.classloading;

import java.io.IOException;
import java.io.InputStream;

public class GenericFactory {

	/**
	 * Crea una nuova istanza dell'oggetto rappresentato dallo stream passato
	 * come input.
	 * 
	 * @param qualifiedName
	 *            il binary name della classe da caricare
	 * @param inputStream
	 *            l'input stream contenente la classe da caricare
	 * @param loader
	 *            l'istanza del generic class loader
	 * @return la nuova istanza
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IOException
	 */
	public static Object create(String qualifiedName, InputStream inputStream, GenericClassLoader loader)
			throws InstantiationException, IllegalAccessException, IOException {

		if (loader == null)
			throw new IllegalArgumentException();

		return loader.loadClass(qualifiedName, inputStream).newInstance();
	}

	/**
	 * Crea una nuova istanza dell'oggetto rappresentato dallo stream passato
	 * come input.
	 * 
	 * @param qualifiedName
	 *            il binary name della classe da caricare
	 * @param inputStream
	 *            l'input stream contenente la classe da caricare
	 * @param clLoader
	 *            l'istanza del class loader
	 * @return la nuova istanza
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IOException
	 */
	public static Object create(String qualifiedName, InputStream inputStream, ClassLoader clLoader)
			throws InstantiationException, IllegalAccessException, IOException {

		return create(qualifiedName, inputStream, getClassLoader(clLoader));
	}

	/**
	 * Crea una nuova istanza dell'oggetto rappresentato dallo stream passato
	 * come input.
	 * 
	 * @param qualifiedName
	 *            il binary name della classe da caricare
	 * @param inputStream
	 *            l'input stream contenente la classe da caricare
	 * @param cl
	 *            la classe dalla quale ottenere il class loader
	 * @return la nuova istanza
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IOException
	 */
	public static Object create(String qualifiedName, InputStream inputStream, Class<?> cl)
			throws InstantiationException, IllegalAccessException, IOException {

		return create(qualifiedName, inputStream, getClassLoader(cl));
	}

	/**
	 * Crea un'istanza del generic class loader.
	 * 
	 * @param cl
	 *            la classe dalla quale ottenere il class loader
	 * @return the classLoader
	 */
	public static GenericClassLoader getClassLoader(Class<?> cl) {

		GenericClassLoader loader = null;

		if (cl != null) {
			loader = new GenericClassLoader(cl);
		} else
			throw new IllegalArgumentException();

		return loader;
	}

	/**
	 * Crea un'istanza del generic class loader.
	 * 
	 * @param clLoader
	 *            l'istanza del class loader
	 * @return the classLoader
	 */
	public static GenericClassLoader getClassLoader(ClassLoader clLoader) {

		GenericClassLoader loader = null;

		if (clLoader != null) {
			if (clLoader instanceof GenericClassLoader) {
				loader = (GenericClassLoader) clLoader;
			} else {
				loader = new GenericClassLoader(clLoader);
			}
		} else
			throw new IllegalArgumentException();

		return loader;
	}
}
