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

        // 1. ʵ��Runnable
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

        // 2. �����¼�����
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

        // 3. ���б���е���
        fruits.forEach( s -> Log.v(s));
//        fruits.forEach( Log::v);

        Log.v("=============");
        // ʹ��Java 8�ķ������ø�����
        fruits.forEach(System.out::println);

        // 4. ʹ��lambda���ʽ�ͺ���ʽ�ӿ�Predicate
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        Log.v("Languages which starts with J :");
        filter(languages,(str)->str.startsWith("J"));

        Log.v("Languages which ends with a ");
        filterEx(languages,(str)->str.endsWith("a"));

        Log.v("Print all languages :");
        filter(languages,str -> true);

        Log.v("Print no languages :");
        filter(languages,str -> false);

        // 5. �����lambda���ʽ�м���Predicate
        Log.v("filterComlicated :");
        filterComlicated(languages);

        // 6. ʹ��lambda���ʽ��Map��Reduceʾ��
        // ��ʹ��lambda���ʽΪÿ����������12%��˰
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
                .map((cost) -> cost + cost * 0.12) // ���б��ÿ��Ԫ��ʹ��ĳ������,�ʺ���map
                .reduce((sum ,cost) -> sum + cost).get();
        Log.v("total:"+bill);


        // 7. ͨ�����˴���һ��String�б�
        List<String> strList = Arrays.asList("aaaa","bbbb","ccc","d","ee");
        List<String> filtered = strList.stream().filter( str -> str.length() > 2 ).collect(Collectors.toList());
        strList.forEach(System.out::println);
        Log.v("========");
        filtered.forEach(System.out::println);

        // 8. ���б��ÿ��Ԫ��Ӧ�ú���
        // ���ַ������ɴ�д���ö�����������
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        String G7Countries = G7.stream().map( str -> str.toUpperCase()).collect(Collectors.joining(", "));
        Log.v(G7Countries);

        // 9. ���Ʋ�ͬ��ֵ������һ�����б�.ʹ��distinctȥ��
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4,9,10,1,2);
        List<Integer> distinct = numbers.stream()
                .map( i -> i*i)
                .distinct()
                .collect(Collectors.toList());
        distinct.forEach(Log::v); // �ȼ���"msg -> Log.v(msg)"
//        distinct.forEach(msg -> Log.v(msg));
//        distinct.forEach(System.out::println);

        // 10. ���㼯��Ԫ�ص����ֵ����Сֵ���ܺ��Լ�ƽ��ֵ
        //��ȡ���ֵĸ�������Сֵ�����ֵ���ܺ��Լ�ƽ��ֵ
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
