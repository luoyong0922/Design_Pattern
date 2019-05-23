package com.roy.singleton;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CountDownLatch;

/**
 * @author xichengxml
 * @date 2019/1/25 15:02
 * 该类用于模拟高并发请求，参考文档
 * @see <a href="http://www.importnew.com/30073.html"></a>
 */
public class MockMultiThreadCreateSingleTonTest {
    private static final int NUM = 20;
    private static int count = 0;
    private static Singleton1 singleton1 = null;
    private static Singleton2 singleton2 = null;
    private static Singleton3 singleton3 = null;

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(NUM);
        System.out.println("Ready, Go!");
        waitAllArrrived(countDownLatch);
        try {
            // 阻塞等待
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished");
    }

    /**
     * @return
     * 任务启动，到达一个计数减1，都到达后开启gate
     */
    private static void waitAllArrrived(CountDownLatch countDownLatch) {
        for (int i = 0; i < NUM; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        bizCode();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            };
            t.start();
        }
    }

    /**
     * 业务调用，对数字进行加1操作
     */
    private static void bizCode() {
//        for (int i = 0; i < 10; i++) {
//            count++;
//            System.out.println("This is bizCode--" + i + "count:" + count);
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        constructorGetClass();
        singleton1 = Singleton1.getSingleton();
        System.out.println("singleton1:"+singleton1.hashCode());
        singleton2 = Singleton2.getSingleton();
        System.out.println("singleton2:"+singleton2.hashCode());
        singleton3 = Singleton3.INSTANCE;
        System.out.println("singleton3:"+singleton3.hashCode());
        System.out.println("singleton3 name:"+singleton3.name());
        EnumSingleton enumSingleton = EnumSingleton.RUN;
        System.out.println("enumSingleton:"+enumSingleton.hashCode());
    }

    // 通过反射方式，将会得到新的单例
    public static void constructorGetClass(){
        Class c = null;
        try {
            c = Class . forName ( Singleton1. class . getName ( ) );
            Constructor ct = c . getDeclaredConstructor ( ) ;
            ct . setAccessible ( true ) ;
            Singleton1 singleton = ( Singleton1 ) ct . newInstance ( ) ;
            System.out.println("singleton:"+singleton.hashCode());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}