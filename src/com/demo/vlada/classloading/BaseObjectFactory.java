package com.demo.vlada.classloading;

import java.io.IOException;
import java.io.InputStream;
import com.demo.vlada.interfaces.LocalModule;

public class BaseObjectFactory {
	
	private static BaseObjectFactory genericFactory = new BaseObjectFactory();
	private static MyClassLoader gcl;
	   
    private BaseObjectFactory(){ 
    	gcl = new MyClassLoader(LocalModule.class.getClassLoader());
    }

	public static Object create(String qualifiedName, InputStream inputStream)
			throws InstantiationException, IllegalAccessException, IOException {

		return gcl.loadClass(qualifiedName, inputStream).newInstance();
	}
	
}
