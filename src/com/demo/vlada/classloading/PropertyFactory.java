package com.demo.vlada.classloading;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.vlada.entities.PersistedFile;
import com.demo.vlada.services.FileService;

public class PropertyFactory {

	@Autowired
	private FileService fileService;

	public ResourceBundle create(String propertyFileName) throws IOException {
		ResourceBundle bundle = null;
		PersistedFile pf = (PersistedFile) fileService.getFileByName(propertyFileName + ".properties");
		InputStream myInputStream = new ByteArrayInputStream(pf.getFileBytes());
		bundle = new PropertyResourceBundle(myInputStream);
		return bundle;
	}
	
	public ResourceBundle create(int fileNameId) throws IOException {
		ResourceBundle bundle = null;
		PersistedFile pf = (PersistedFile) fileService.getPersistedFileById(fileNameId);
		InputStream myInputStream = new ByteArrayInputStream(pf.getFileBytes());
		bundle = new PropertyResourceBundle(myInputStream);
		return bundle;
	}

}
