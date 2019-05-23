package com.roy.multiThread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 实例1，使用CountDownLatch模拟高并发
 * 场景化：有一道阻塞墙，站着一个管理员，手里拿着N（线程数）个牌子，
 * 到达一个线程，把牌子给该线程，当手里牌子都发完时，打开墙门，放行所有线程通过
 */

/**
 * @author xichengxml
 * @date 2019/1/25 15:02
 * 该类用于模拟高并发请求，参考文档
 * @see <a href="http://www.importnew.com/30073.html"></a>
 */
public class MockMultiThreadByCountDownLatch {

    private static final int NUM = 5;
    private static int count = 0;

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
//        作者：西城xml
//        来源：CSDN
//        原文：https://blog.csdn.net/xichengqc/article/details/86673159
//        版权声明：本文为博主原创文章，转载请附上博文链接！
