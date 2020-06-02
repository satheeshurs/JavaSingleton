package com.singleton.clone;

/**
 * Clonning a object will violate Singleton
 * <br><b>Fix:</b> Override clone() method and return object instance of using super.clone()
 */
public class CloneSingleton implements  Cloneable{
    private static CloneSingleton cloneSingleton;

    private CloneSingleton(){

    }

    public static CloneSingleton getInstance(){
        if(cloneSingleton == null){
            cloneSingleton = new CloneSingleton();
        }
        return  cloneSingleton;
    }
    public static void main(String[] args) {
        CloneSingleton.getInstance();
        System.out.println(cloneSingleton.hashCode());
        try {
            System.out.println(cloneSingleton.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return cloneSingleton.hashCode();
    }
}
