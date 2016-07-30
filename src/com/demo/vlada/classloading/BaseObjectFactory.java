package com.demo.vlada.classloading;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.vlada.entities.PersistedFile;
import com.demo.vlada.services.FileService;

public class BaseObjectFactory {
	private String packageName = "com.demo.vlada.classes.baseobject.";
	
	@Autowired
	private FileService fileService;
	
	public Object create(String name) throws InstantiationException, IllegalAccessException, IOException {
		String qualifiedName=packageName+name;
		Object o = ObjectFactory.findClass(qualifiedName);
		if (o == null){
			PersistedFile pf = (PersistedFile)fileService.getFileByName(name+".class");
			InputStream myInputStream = new ByteArrayInputStream(pf.getFileBytes());
			o = ObjectFactory.create(qualifiedName, myInputStream);
		}
		return o;
	}
	
}
