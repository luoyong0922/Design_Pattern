package com.roy.singleton;

import org.junit.Test;

public class EnumTest {
    @Test
    public  void test1() {
        EnumSingleton enumSingleton = EnumSingleton.RUN;
        System.out.println(enumSingleton.hashCode());
        enumSingleton.doSomething();
        enumSingleton = EnumSingleton.INSTANCE;
        System.out.println(enumSingleton.hashCode());
        enumSingleton.doSomething();
        enumSingleton = EnumSingleton.RUN;
        System.out.println(enumSingleton.hashCode());
        enumSingleton.doSomething();

    }
}
