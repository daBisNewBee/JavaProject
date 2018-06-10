package basic.defalut;

public interface Animal {

    void speek();


    /*
    *
    * 增加具体的方法， 是为了既有的成千上万的Java类库的类增加新的功能，
    * 且不必对这些类重新进行设计。 比如， 只需在Collection接口中
        增加default Stream<E> stream(), 相应的Set和List接口以及它们的子类
        都包含此的方法， 不必为每个子类都重新copy这个方法。
    * */
    default void say(){
        System.out.println("this is in animal say.");
    };

}
