package com.demo.vlada.classloading;

import java.io.IOException;
import java.io.InputStream;
import com.demo.vlada.interfaces.LocalModule;

public class GenericFactory {
	
	private static GenericFactory genericFactory = new GenericFactory();
	private static GenericClassLoader gcl;
	   
    private GenericFactory(){ 
    	gcl = new GenericClassLoader(LocalModule.class.getClassLoader());
    }

	public static Object create(String qualifiedName, InputStream inputStream)
			throws InstantiationException, IllegalAccessException, IOException {

		return gcl.loadClass(qualifiedName, inputStream).newInstance();
	}
	
}
