package com.demo.vlada.classloading;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GenericClassLoader extends ClassLoader{
	
	/**
	 * @param clLoader il class loader
	 */
	public GenericClassLoader(ClassLoader clLoader){
		super(clLoader);
	}
	/**
	 * @param cl l'oggetto class dal quale ottenere il class loader
	 */
	public GenericClassLoader(Class<?> cl){
		super(cl.getClassLoader());
	}
	
	/** 
	 * @param name il binary name della classe da caricare
	 * @param inputStream l'input stream contenente la classe da caricare
	 * @return
	 * @throws IOException
	 */
	public Class<?> loadClass(String name, InputStream inputStream) throws IOException {
		byte[] bytea = this.read((ByteArrayInputStream) inputStream);
		Class<?> c = defineClass(name, bytea, 0, bytea.length);
		return (Class<?>) c;
	}
	
	/**
	 * @param bais l'input stream da convertire in un array di byte
	 * @return
	 * @throws IOException
	 */
	private byte[] read(ByteArrayInputStream bais) throws IOException {
	     byte[] array = new byte[bais.available()];
	     bais.read(array);

	     return array;
	}
}
