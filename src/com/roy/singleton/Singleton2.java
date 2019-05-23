package com.roy.singleton;

/**
 * 2. 采用静态内部类方法实现单例
 * 注意：需要额外的工作(Serializable、transient、readResolve())来实现序列化。
 *
 * 不管采取何种方案，请时刻牢记单例的三大要点：
 *
 * 线程安全
 * 延迟加载
 * 序列化与反序列化安全
 */
public class Singleton2 {
        private static class Holder {
            private static Singleton2 singleton = new Singleton2();
        }

        private Singleton2(){}

        public static Singleton2 getSingleton(){
            return Holder.singleton;
        }
}
