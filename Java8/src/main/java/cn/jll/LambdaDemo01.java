/**
 * 了解如何修改一个匿名内部类的方式，转而使用Lambda表达式的方式
 */
package cn.jll;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

public class LambdaDemo01 {

    public static void main(String[] args) {

//        // 以线程为一个例子
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                System.out.println("以匿名内部类的方式启动一个线程！");
//            }
//        }).start();
//
//        new Thread(() -> {
//            System.out.println("以优化匿名内部类的方式，也就是Lambda的方式启动一个线程");
//        }).start();

        // 开始学习的时候，使用匿名内部类写出来。
        // 为什么是匿名内部类呢？是因为是一个接口，而且接口中只有一个方法。
        int res = calculateNum(new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left + right;
            }
        });
        System.out.println(res);

        // 这个匿名内部类可以被优化吗？
        // 1. 是一个接口
        // 2. 只有一个抽象方法需要重写，IntBinaryOperator 只有一个抽象方法需要重写。
        // 可以使用Lambda表达式
        // 写完匿名内部类 alt+回车 就会自动提示修改为 lambda表达式的方法
        // 如果看不懂lambda表达式的方式，alt+回车，就会自动转换成为 匿名内部类的方式

        int res1 = calculateNum((int left, int right) -> {return left + right;});
        System.out.println(res1);

        printNum((new IntPredicate() {
            @Override
            public boolean test(int value) {
                return value > 5;
            }
        }));

        printNum(((int value) -> {return value > 5;}));
        // 其实IntPredicate 这种接口的test方法就是没有被定义的。
        // 如果需要使用，就需要在匿名内部类中实现test方法，实现printNum中使用到的IntPredicate的方法
        // 然后对匿名内部类进行优化就是直接写test的方法，忽略方法名字，考虑参数和方法的实现方法。


        // 第一个泛型被指定好了式String，第二个泛型可以自己指定
        Integer resInt = typeConver(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.valueOf(s);
            }
        });
        System.out.println(resInt);

        // 第一个泛型被指定好了式String，第二个泛型可以自己指定
        Integer resInt2 = typeConver((String s) -> {
                return Integer.valueOf(s);});
        System.out.println(resInt2);


        foreachArr(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.print(value);
            }
        });
        System.out.println();

        foreachArr((int value) -> {System.out.print(value);});
        System.out.println();

        System.out.println("所有线程自动停止！程序退出");
    }


    public static int calculateNum(IntBinaryOperator operator){
        int a = 20;
        int b = 20;
        return operator.applyAsInt(a,b);
    }

    public static void printNum(IntPredicate predicate){
        int[] arr = {1,2,3,4,5,6,7,8,9,10};

        for (int i: arr){
            if (predicate.test(i)){
                System.out.print(i);
            }
        }

        System.out.println();
    }

    /**
     * 使用方法泛型, 在这个使用泛型的方法中，其中一个泛型已经被定义为String，另一个泛型可以自己定义。
     * 泛型就是可以自己定义参数的类型，返回值的类型等。
     * 这里把方法定义成接口类型的，好处就是每一次调用方法接口的时候可以实现不同的方法操作。
     * 这种方式支持用户自己去定义，传入匿名内部类对象，自己实现方法。就会有不同的效果
     * 这就是将方法定义成接口的方法。
     * @param func
     * @return
     * @param <R>
     */
    public static <R> R typeConver(Function<String, R> func){
        String str = "123";
        R result = func.apply(str);
        return result;
    };

    /**
     *
     * @param consumer
     */
    public static void foreachArr (IntConsumer consumer) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        for (int i: arr){
            consumer.accept(i);
        }
    }
}
