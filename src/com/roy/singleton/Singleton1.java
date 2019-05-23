package com.roy.singleton;

import java.io.Serializable;

/**
 * 这种写法被称为“双重检查锁”，顾名思义，就是在getSingleton()方法中，进行两次null检查。
 * 看似多此一举，但实际上却极大提升了并发度，进而提升了性能。为什么可以提高并发度呢？
 * 就像上文说的，在单例中new的情况非常少，绝大多数都是可以并行的读操作。
 * 因此在加锁前多进行一次null检查就可以减少绝大多数的加锁操作，执行效率提高的目的也就达到了。
 *
 * 坑
 *
 * 那么，这种写法是不是绝对安全呢？前面说了，从语义角度来看，并没有什么问题。但是其实还是有坑。
 * 说这个坑之前我们要先来看看volatile这个关键字。其实这个关键字有两层语义。
 * 第一层语义相信大家都比较熟悉，就是可见性。
 * 可见性指的是在一个线程中对该变量的修改会马上由工作内存（Work Memory）写回主内存（Main Memory），
 * 所以会马上反应在其它线程的读取操作中。顺便一提，
 * 工作内存和主内存可以近似理解为实际电脑中的高速缓存和主存，
 * 工作内存是线程独享的，主存是线程共享的。
 * volatile的第二层语义是禁止指令重排序优化。大家知道我们写的代码（尤其是多线程代码），
 * 由于编译器优化，在实际执行的时候可能与我们编写的顺序不同。编译器只保证程序执行结果与源代码相同，
 * 却不保证实际指令的顺序与源代码相同。这在单线程看起来没什么问题，然而一旦引入多线程，
 * 这种乱序就可能导致严重问题。volatile关键字就可以从语义上解决这个问题。
 *
 * 注意，前面反复提到“从语义上讲是没有问题的”，但是很不幸，
 * 禁止指令重排优化这条语义直到jdk1.5以后才能正确工作。
 * 此前的JDK中即使将变量声明为volatile也无法完全避免重排序所导致的问题。
 * 所以，在jdk1.5版本前，双重检查锁形式的单例模式是无法保证线程安全的。
 * 而且需要额外的工作(Serializable、transient、readResolve())来实现序列化，
 * 否则每次反序列化一个序列化的对象实例时都会创建一个新的实例。
 */
public class Singleton1 implements Serializable {
        private static volatile Singleton1 singleton = null;

        private Singleton1(){}
//     双重校验锁
        public static Singleton1 getSingleton(){
            if(singleton == null){
                synchronized (Singleton1.class){
                    if(singleton == null){
                        singleton = new Singleton1();
                    }
                }
            }
            return singleton;
        }

        private Object readResolve(){
            return singleton;
        }
}
