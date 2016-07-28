package com.demo.vlada.classloading;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.demo.vlada.interfaces.LocalModule;

public class GenericClassLoader extends ClassLoader {

	/**
	 * @param clLoader
	 *            il class loader
	 */
	public GenericClassLoader(ClassLoader clLoader) {
		super(clLoader);
		System.out.println("xxxxxxxxxxxxxxxxxxxxxx ClassLoader: " + clLoader);
	}

	/**
	 * @param cl
	 *            l'oggetto class dal quale ottenere il class loader
	 */
	public GenericClassLoader(Class<?> cl) {
		super(cl.getClassLoader());
		System.out.println("xxxxxxxxxxxxxxxxxxxxxx ClassLoader: " + cl.getClassLoader());
	}

	/**
	 * @param name
	 *            il binary name della classe da caricare
	 * @param inputStream
	 *            l'input stream contenente la classe da caricare
	 * @return
	 * @throws IOException
	 */
	public Class<?> loadClass(String name, InputStream inputStream) throws IOException {
		Class<?> c = findLoadedClass(name);
		System.out.println("Ovo je classLoader sa GenericClassLoader.class.get...: "+GenericClassLoader.class.getClassLoader());
		System.out.println("Ovo je classLoader sa this.getClass(): "+this.getClass().getClassLoader());
		System.out.println("Ovo je parent classLoader: "+getParent());
		if (c == null) {
			try {
				c = getParent().loadClass(name);
			} catch (Exception ex) {
				System.out.println("Ovo je vrednost loadovane klase: "+c);
				System.out.println("Ovo je exception iz ClassLoadera: "+ex);
			}
		}
		if (c == null) {
			byte[] bytea = this.read((ByteArrayInputStream) inputStream);
			c = defineClass(name, bytea, 0, bytea.length);
			System.out.println("Ovo je defineClass metoda za ime klase: "+name+"   byte length:"+bytea.length);
		}
		return (Class<?>) c;
	}

	
	/**
	 * @param bais
	 *            l'input stream da convertire in un array di byte
	 * @return
	 * @throws IOException
	 */
	private byte[] read(ByteArrayInputStream bais) throws IOException {
		byte[] array = new byte[bais.available()];
		bais.read(array);

		return array;
	}
}
