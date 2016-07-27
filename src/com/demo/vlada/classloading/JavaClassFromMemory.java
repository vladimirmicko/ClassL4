package com.demo.vlada.classloading;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class JavaClassFromMemory extends SimpleJavaFileObject {
    /** A class bytes output stream. */
    private final OutputStream os = new ByteArrayOutputStream();

    /**
     * Construct the class with initialization.
     *
     * @param name A class name.
     */
    protected JavaClassFromMemory(String className) {
        super(URI.create("memory:///" + className.replace('.', '/') +
                Kind.CLASS.extension), Kind.CLASS);
    }

    /*
     * @see javax.tools.SimpleJavaFileObject#openOutputStream()
     */
    @Override
    public OutputStream openOutputStream() throws IOException {
        return this.os;
    }
}