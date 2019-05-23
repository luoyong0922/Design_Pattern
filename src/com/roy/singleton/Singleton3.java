package com.roy.singleton;

/**
 * 使用枚举除了线程安全和防止反射强行调用构造器之外，还提供了自动序列化机制，
 * 防止反序列化的时候创建新的对象。因此，Effective Java推荐尽可能地使用枚举来实现单例。
 */

/**
 * 单例模式优缺点：
 *
 * 优点
 *
 * 单例模式在内存中只有一个实例，减少了内存开支，尤其是频繁的创建和销毁实例。
 * 由于只生成一个实例，所以减少了系统的性能开销。
 * 避免对资源的多重占用，例如写文件操作。
 * 单例模式可以在系统设置全局的访问点，优化和共享资源访问。
 *
 * 缺点
 *
 * 单例模式不易扩展，若要扩展，除了修改代码外别无他法。
 * 单例模式对测试不利。
 * 单例模式与单一职责原则有冲突，一个类应该只实现一个逻辑，而不用关心它是否是单例的。
 *
 * 作者：xeblog
 * 链接：https://juejin.im/post/5ce56e9e518825332d13c266
 * 来源：掘金
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public enum Singleton3 {
    INSTANCE;
    private String name;
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
