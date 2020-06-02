package com.singleton.reflection.multiclassloader;

/**
 * Multiple class loaders will create multiple instance for a singleton class
 * <br><b>Fix:</b> Create object eagerly and through Runtime exception in the private constructor
 */
public class MultipileClassLoader {
    private static MultipileClassLoader multipileClassLoader = new MultipileClassLoader();
    private MultipileClassLoader(){
        throw new RuntimeException("Can not create instance, please use getInstance to get an Instance");

    }

    public static MultipileClassLoader getInstance(){
       return  multipileClassLoader;
    }

    public static void main(String[] args) {
        try {
            System.out.println(MultipileClassLoader.getClass("com.singleton.reflection.multiclassloader.MultipileClassLoader").newInstance().hashCode());
            System.out.println(MultipileClassLoader.getClass("com.singleton.reflection.multiclassloader.MultipileClassLoader").newInstance().hashCode());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static Class getClass(String classname)  throws ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if(classLoader == null)
            classLoader = MultipileClassLoader.class.getClassLoader();
        return (classLoader.loadClass(classname));
    }
}
