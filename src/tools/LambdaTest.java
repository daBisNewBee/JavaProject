package tools;

import utils.Log;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.sun.tools.doclint.Entity.sum;


public class LambdaTest {

    public static void main(String[] args) {

        new Thread(){
            @Override
            public void run() {
                super.run();
                Log.v("111111");
            }
        }.start();

        // 1. 实现Runnable
        new Thread( () ->
                Log.v("222222222") ).start();
        new Thread( () ->
                { Log.v("333333333"); Log.v("44444444");}).start();

        /*
        JButton show = new JButton("Show");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Log.v("Event handling without lambda expression is boring");
            }
        });

        // 2. 进行事件处理
        show.addActionListener( (e) ->
                Log.v("Light, Camera, Action !! Lambda expressions Rocks"));
        */


        List<String> fruits = Arrays.asList("apple","orange","putao");
        for (String fruit :
                fruits) {
//            Log.v(fruit);
        }

        fruits.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
//                Log.v(s);
            }
        });

        // 3. 对列表进行迭代
        fruits.forEach( s -> Log.v(s));
//        fruits.forEach( Log::v);

        Log.v("=============");
        // 使用Java 8的方法引用更方便
        fruits.forEach(System.out::println);

        // 4. 使用lambda表达式和函数式接口Predicate
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        Log.v("Languages which starts with J :");
        filter(languages,(str)->str.startsWith("J"));

        Log.v("Languages which ends with a ");
        filterEx(languages,(str)->str.endsWith("a"));

        Log.v("Print all languages :");
        filter(languages,str -> true);

        Log.v("Print no languages :");
        filter(languages,str -> false);

        // 5. 如何在lambda表达式中加入Predicate
        Log.v("filterComlicated :");
        filterComlicated(languages);

        // 6. 使用lambda表达式的Map和Reduce示例
        // 不使用lambda表达式为每个订单加上12%的税
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double bill = 0;
        for (Integer cost :
                costBeforeTax) {
            double price = cost + cost * 0.12;
            bill += price;
            Log.v(price+"");
        }
        Log.v("total:"+bill);

        costBeforeTax.stream()
                .map(cost -> cost + cost * 0.12)
                .forEach(System.out::println);

        bill = costBeforeTax.stream()
                .map((cost) -> cost + cost * 0.12) // 对列表的每个元素使用某个函数,适合用map
                .reduce((sum ,cost) -> sum + cost).get();
        Log.v("total:"+bill);


        // 7. 通过过滤创建一个String列表
        List<String> strList = Arrays.asList("aaaa","bbbb","ccc","d","ee");
        List<String> filtered = strList.stream().filter( str -> str.length() > 2 ).collect(Collectors.toList());
        strList.forEach(System.out::println);
        Log.v("========");
        filtered.forEach(System.out::println);

        // 8. 对列表的每个元素应用函数
        // 将字符串换成大写并用逗号链接起来
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        String G7Countries = G7.stream().map( str -> str.toUpperCase()).collect(Collectors.joining(", "));
        Log.v(G7Countries);

        // 9. 复制不同的值，创建一个子列表.使用distinct去重
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4,9,10,1,2);
        List<Integer> distinct = numbers.stream()
                .map( i -> i*i)
                .distinct()
                .collect(Collectors.toList());
        distinct.forEach(Log::v); // 等价于"msg -> Log.v(msg)"
//        distinct.forEach(msg -> Log.v(msg));
//        distinct.forEach(System.out::println);

        // 10. 计算集合元素的最大值、最小值、总和以及平均值
        //获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt(x -> x).summaryStatistics();
        Log.v("Highest:"+stats.getMax());
        Log.v("Lowest:"+stats.getMin());
        Log.v("Sum:"+stats.getSum());
        Log.v("Average:"+stats.getAverage());


    }

    public static void filterComlicated(List<String> names){
        Predicate<String> startWithJ = str -> str.startsWith("J");
        Predicate<String> fourLetterLong = str -> str.length() == 4;
        names.stream()
//                .filter(startWithJ.negate())
//                .filter(startWithJ.or(fourLetterLong))
                .filter(startWithJ.and(fourLetterLong))
                .forEach(System.out::println);

    }

    public static void filterEx(List<String> names, Predicate<String> condition){
        names.stream()
                .filter(name -> (condition.test(name)))
                .forEach(System.out::println);
    }
    public static void filter(List<String> names, Predicate<String> condition){
        for (String name :
                names) {
            if (condition.test(name))
                Log.v(name + " ");
        }
    }

}
