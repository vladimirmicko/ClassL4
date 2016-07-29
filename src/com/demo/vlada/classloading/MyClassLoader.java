package com.demo.vlada.classloading;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {

	public MyClassLoader(ClassLoader clLoader) {
		super(clLoader);
	}

	public MyClassLoader(Class<?> cl) {
		super(cl.getClassLoader());
	}


	public Class<?> loadClass(String name, InputStream inputStream) throws IOException {
		Class<?> c = findLoadedClass(name);
		if (c == null) {
			try {
				c = getParent().loadClass(name);
			} catch (Exception ex) {
				System.out.println("This is the caught exception from ClassLoadera: "+ex);
			}
		}
		if (c == null) {
			byte[] bytea = this.read((ByteArrayInputStream) inputStream);
			c = defineClass(name, bytea, 0, bytea.length);
			System.out.println("OdefineClass method for ClassName: "+name+"   byte length:"+bytea.length);
		}
		return (Class<?>) c;
	}

	
	private byte[] read(ByteArrayInputStream bais) throws IOException {
		byte[] array = new byte[bais.available()];
		bais.read(array);

		return array;
	}
}
