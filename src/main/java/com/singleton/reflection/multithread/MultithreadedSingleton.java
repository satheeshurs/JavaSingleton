package com.singleton.reflection.multithread;

import com.singleton.reflection.multiclassloader.MultipileClassLoader;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Singleton will be violated in singleton
 * <br><b>Fix:</b> Using double check locking synchronized, set variable as volatile to avoid half backed object
 * <br> can use Holder which is kind of eager loading
 */
public class MultithreadedSingleton {
    private static volatile MultithreadedSingleton multithreadedSingleton;
    
    private MultithreadedSingleton(){

    }
    static class Holder{
        MultithreadedSingleton INSTANCE = new MultithreadedSingleton();
    }
    public static MultithreadedSingleton getInstance(){//synchronized
        //double check locks
        if (multithreadedSingleton == null) {
            synchronized (MultithreadedSingleton.class){
                if(multithreadedSingleton == null) {
                    multithreadedSingleton = new MultithreadedSingleton();
                }
            }


        }
        return multithreadedSingleton;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(MultithreadedSingleton::useSingleTon);
        executorService.submit(MultithreadedSingleton::useSingleTon);
        executorService.shutdown();

    }

    public static void useSingleTon() {
        System.out.println(MultithreadedSingleton.getInstance().hashCode());
    }
}
