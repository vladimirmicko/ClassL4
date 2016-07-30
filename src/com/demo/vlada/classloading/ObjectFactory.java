package com.demo.vlada.classloading;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.vlada.classes.baseobject.interfaces.BaseObject;
import com.demo.vlada.classes.baseobject.interfaces.Model1;
import com.demo.vlada.entities.PersistedFile;
import com.demo.vlada.services.FileService;


public class ObjectFactory {
	
	private static ObjectFactory objectFactory = new ObjectFactory();
	private static MyClassLoader myClassLoader;

	private ObjectFactory() {
		myClassLoader = new MyClassLoader(BaseObject.class.getClassLoader());
	}

	public static Object findClass(String qualifiedName) throws InstantiationException, IllegalAccessException {
		Object o;
		Class<?> c = myClassLoader.findClass(qualifiedName);
		return (c == null) ? null:c.newInstance();
	}

	public static Object create(String qualifiedName, InputStream inputStream) throws InstantiationException, IllegalAccessException, IOException {
		return myClassLoader.loadClass(qualifiedName, inputStream).newInstance();
	}

	public static MyClassLoader getMyClassLoader() {
		return myClassLoader;
	}
}
