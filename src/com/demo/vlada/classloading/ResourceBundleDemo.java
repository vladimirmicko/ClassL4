package com.demo.vlada.classloading;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

public class ResourceBundleDemo {

   public static void main(String[] args) {

   ClassLoader cl = ClassLoader.getSystemClassLoader();

   // create a new ResourceBundle.Control with default format
   ResourceBundle.Control rbc =
   ResourceBundle.Control.getControl(Control.FORMAT_DEFAULT);

   // create a new ResourceBundle with specified locale
   // and SystemClassLoader and a Control
   ResourceBundle bundle =
   ResourceBundle.getBundle("classLoadTest", Locale.US, cl, rbc);

   // print the text assigned to key "hello"
   System.out.println("" + bundle.getString("hello"));

   }
}
