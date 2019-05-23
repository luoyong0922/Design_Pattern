package com.roy.singleton;

public enum  EnumSingleton {
    INSTANCE{
        @Override
        public void doSomething(){
            System.out.println("instance");
        }
    },
    RUN {
        @Override
        public void doSomething() {
            System.out.println("run");
        }
    };
    public abstract void doSomething();
}
