package com.demo.vlada.interfaces;

public class Micko implements com.demo.vlada.interfaces.LocalModule {

	@Override
	public void testAdd() {
		System.out.println("------------------------------------------------- testAdd() metoda iz Micko klase.");
		System.out.println("------------------------ MickoClass - ClassLoader");
		System.out.println("Local module:"+com.demo.vlada.interfaces.LocalModule.class.getClassLoader());
		System.out.println("Micko class:"+com.demo.vlada.interfaces.Micko.class.getClassLoader());
		System.out.println("Micko class with: this.getClass().getClassLoader():"+this.getClass().getClassLoader());
		System.out.println("------------------------ MickoClass - ClassLoader");
		
	}
	
	@Override
	public void testAdd1() {
		System.out.println("------------------------------------------------- testAdd1() metoda iz Micko klase.");
	}

}
