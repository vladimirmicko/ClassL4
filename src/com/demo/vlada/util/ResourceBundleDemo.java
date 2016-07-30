package com.demo.vlada.util;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

/**
 * 
 * @author randjelovicv
 *
 */

public class ResourceBundleDemo {

	public static void main(String[] args) {
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		ResourceBundle.Control rbc = ResourceBundle.Control.getControl(Control.FORMAT_DEFAULT);
		ResourceBundle bundle = ResourceBundle.getBundle("classLoadTest", Locale.US, cl, rbc);
		System.out.println("" + bundle.getString("hello"));
	}
}
