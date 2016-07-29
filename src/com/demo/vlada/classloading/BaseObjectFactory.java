package com.demo.vlada.classloading;

import java.io.IOException;
import java.io.InputStream;
import com.demo.vlada.classes.baseobject.interfaces.BaseObject;

public class BaseObjectFactory {

	private static BaseObjectFactory baseObjectFactory = new BaseObjectFactory();
	private static MyClassLoader gcl;
	private static String packageName = "com.demo.vlada.classes.baseobject.";

	private BaseObjectFactory() {
		gcl = new MyClassLoader(BaseObject.class.getClassLoader());
	}

	public static Object findClass(String name) throws InstantiationException, IllegalAccessException {
		String qualifiedName=packageName+name;
		Object o;
		Class<?> c = gcl.findClass(qualifiedName);
		if (c == null) {
			return null;
		} else {
			o = c.newInstance();
		}
		return o;
	}

	public static Object create(String name, InputStream inputStream)
			throws InstantiationException, IllegalAccessException, IOException {
		String qualifiedName=packageName+name;
		return gcl.loadClass(qualifiedName, inputStream).newInstance();
	}

}
