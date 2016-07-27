package com.demo.vlada.classloading;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LocalClassLoader extends ClassLoader {
    /** Local classes stash. */
    private final Map<String, JavaClassFromMemory> classStash = new HashMap<>();

    /** 
     * Default constructor.
     * @param parent Parent class loader.
     */
    public LocalClassLoader(ClassLoader parent) {
        super(parent);
    }

    /**
     * Create and register new {@link javax.tools.JavaFileObject} object.
     * @param className Name of the class to register.
     *
     * @return Newly created object.
     */
    public JavaClassFromMemory registerNew(String className) {
        JavaClassFromMemory file = new JavaClassFromMemory(className);
        classStash.put(className, file);

        return file;
    }

    /*
     * @see ClassLoader#loadClass(String, boolean)
     */
    @Override
    protected synchronized Class<?> loadClass(String className, boolean resolve)
            throws ClassNotFoundException {

        Class<?> clazz = findLoadedClass(className);

        if (clazz == null) {
            JavaClassFromMemory file = classStash.get(className);

            if(file == null) {
                return super.loadClass(className, resolve);
            }

            ByteArrayOutputStream stream = null;

            try {
                stream = (ByteArrayOutputStream)file.openOutputStream();
                clazz = defineClass(className, stream.toByteArray(), 0, stream.size());
            } catch (IOException e) {
                throw new ClassNotFoundException(e.getMessage());
            }
        }

        if (resolve) {
            resolveClass(clazz);
        }

        return clazz;
    }

}
