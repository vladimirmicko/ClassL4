package com.demo.vlada.interfaces;

public class Cicko extends Micko {
	@Override
	public void testAdd() {
		System.out.println("------------------------------------------------- testAdd() metoda iz Cicko klase.");
		System.out.println("------------------------ CickoClass - ClassLoader");
		System.out.println("LocalModule:"+com.demo.vlada.interfaces.LocalModule.class.getClassLoader());
		System.out.println("MickoClass:"+com.demo.vlada.interfaces.Micko.class.getClassLoader());
		System.out.println("------------------------ CickoClass - ClassLoader");
	} 
	

}
