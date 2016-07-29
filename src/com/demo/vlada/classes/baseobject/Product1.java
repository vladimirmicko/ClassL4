package com.demo.vlada.classes.baseobject;

import com.demo.vlada.classes.baseobject.interfaces.Model1;

public class Product1 implements Model1 {

	@Override
	public int calculate(int a, int b) {
		return a+b;
	}
	

}
