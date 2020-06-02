package com.singleton.serialization;

import java.io.*;

/**
 * By writing an object to stream and reading it back will violate singleton rule
 * <br><b>Fix:</b> in readResolve method return the singleton instance this will make sure alwyas singleton will have one instance
 */
public class SerializationSingleton  implements Serializable {
    private static SerializationSingleton serializationSingleton;

    private SerializationSingleton(){
        System.out.println("Creating");
    }
    public static SerializationSingleton getInstance(){
        if(serializationSingleton == null){
            serializationSingleton = new SerializationSingleton();
        }
        return  serializationSingleton;
    }
    public static void main(String[] args) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("temp.tmp");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            SerializationSingleton serializationSingleton = SerializationSingleton.getInstance();
            System.out.println(serializationSingleton.hashCode());
            objectOutputStream.writeObject(serializationSingleton);
            objectOutputStream.close();
            fileOutputStream.close();

            FileInputStream fileInputStream = new FileInputStream("temp.tmp");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            try {
                System.out.println(objectInputStream.readObject().hashCode());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            fileInputStream.close();
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Object readResolve(){
        return serializationSingleton;
    }
}
