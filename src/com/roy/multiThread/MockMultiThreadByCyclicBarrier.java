package com.roy.multiThread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 使用CyclicBarrier模拟高并发
 * 场景化：有一道阻塞墙，墙上有个门，门上有N（线程数）把锁，
 * 每个线程持有一把钥匙，到达之后就打开自己的锁，全部锁打开后，门开启放行所有线程
 */
public class MockMultiThreadByCyclicBarrier {
    private static final int NUM = 5;
    private static int count = 0;

    public static void main(String[] args) {
        Thread finishThread = new Thread(){
            @Override
            public void run() {
                System.out.println("Finished");
            }
        };
        System.out.println("Ready, Go!");
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUM, finishThread);
        waitAllArrrived(cyclicBarrier);
    }

    public static void waitAllArrrived(CyclicBarrier cyclicBarrier) {
        for (int i = 0; i < NUM; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    bizCode();
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
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
        for (int i = 0; i < 10; i++) {
            count++;
            System.out.println("This is bizCode--" + i + "count:" + count);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
//          作者：西城xml
//          来源：CSDN
//          原文：https://blog.csdn.net/xichengqc/article/details/86673159
//          版权声明：本文为博主原创文章，转载请附上博文链接！