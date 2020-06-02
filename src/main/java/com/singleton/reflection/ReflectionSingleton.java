package com.singleton.reflection;

import java.sql.Ref;

/**
 * using reflection constructor access can be changed from private to public
 * <br><b>Fix:</b> when object is already created through RunTimeException in the constructor so second object wont be created
 * In the constructor assign this object to the static object
 */
public class ReflectionSingleton {
    private static ReflectionSingleton reflectionSingleton;

    private ReflectionSingleton(){
        if(reflectionSingleton != null){
            throw new RuntimeException("Can not created instance, please use getInstance to get an Instance");
        }else{
            reflectionSingleton = this;
        }
    }
    public static ReflectionSingleton getInstance(){
        if(reflectionSingleton == null){
            reflectionSingleton = new ReflectionSingleton();
        }
        return reflectionSingleton;
    }

    public static void main(String... args){
        try {
           Class<ReflectionSingleton> reflectionSingletonClass =
                   (Class<ReflectionSingleton>) Class.forName("com.singleton.reflection.ReflectionSingleton");
           reflectionSingletonClass.getDeclaredConstructor().setAccessible(true);
           System.out.println(reflectionSingletonClass.newInstance().hashCode());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println(ReflectionSingleton.getInstance().hashCode());
    }
}
