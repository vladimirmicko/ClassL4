package com.demo.vlada.interfaces;

public class Micko implements com.demo.vlada.interfaces.LocalModule {

	@Override
	public void testAdd() {
		System.out.println("------------------------------------------------- testAdd() metoda iz Micko klase.");
		System.out.println("------------------------ MickoClass - ClassLoader");
		System.out.println(com.demo.vlada.interfaces.LocalModule.class.getClassLoader());
		System.out.println(com.demo.vlada.interfaces.Micko.class.getClassLoader());
		System.out.println("------------------------ MickoClass - ClassLoader");
	}
	
	@Override
	public void testAdd1() {
		System.out.println("------------------------------------------------- testAdd1() metoda iz Micko klase.");
	}

}
