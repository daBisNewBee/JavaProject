package jvm;

import utils.Log;

/**
 *
 * 栈溢出测试:StackOverflowError
 *
 * -Xss128k：设置每个线程的栈大小
 *
 * "栈"指的是"虚拟机栈"
 *
 * java -Xss1m -classpath ./ jvm.StackErrorMock
 *
 * call()
 * userdeMacBook-Pro:JavaProject user$ java -Xss1m -classpath ./ jvm.StackErrorMock
 index:21824
 *
 * userdeMacBook-Pro:JavaProject user$ java -Xss4m -classpath ./ jvm.StackErrorMock
 index:205132
 *
 * userdeMacBook-Pro:JavaProject user$ java -Xss40m -classpath ./ jvm.StackErrorMock
 index:2564816
 *
 * java -Xss1m -classpath ./ jvm.StackErrorMock
 *
 * call(int i,int j,int m)
 * userdeMacBook-Pro:JavaProject user$ java -Xss1m -classpath ./ jvm.StackErrorMock
 index:15564
 *
 * long a = 10L,b = 200000L, c= 300000000L,aa = 10L,bb = 200000L, cc= 300000000L;
 * userdeMacBook-Pro:JavaProject user$ java -Xss1m -classpath ./ jvm.StackErrorMock
 index:11764
 Throwable:
 *
 */
public class StackErrorMock {

    private static int index = 1;

    public void call(){
//    public void call(int i,int j,int m){
        index++;
//        long a = 10L,b = 200000L, c= 300000000L,aa = 10L,bb = 200000L, cc= 300000000L;
        call();
//        call(i,j,m);
    }


    public static void main(String[] args){

//        int a = 5, b = 50 ,c =0,d =1;
//        System.out.println("|| sum = " + (a==b || (c==d)));


        fabonacci(9999);


        StackErrorMock stackErrorMock = new StackErrorMock();
        try {
            stackErrorMock.call();
//            stackErrorMock.call(1,2,3);
        }catch (Throwable t){// 不是Exception
            // 22624、22198....每次都不一样
            Log.v("index:"+stackErrorMock.index,t);
//            t.printStackTrace();
            /*
            *   index:21952
                Throwable:
                null
                java.lang.StackOverflowError
                    at jvm.StackErrorMock.call(StackErrorMock.java:11)
            * */
        }
    }

    /*
    *
    * 斐波那契数数列
    *
    * { 1,1,2,3,5,8,13,21,...... }
    *
    * */
    public static int fabonacci(int n){
        if (n < 0 || n > 10000) throw new IllegalArgumentException("bad n");
        if (n == 0 || n == 1)
            return 1;

        System.out.println("n = " + n);
//        System.out.println("n = " + n);
        return fabonacci(n -1)  + fabonacci(n-2);
    }

}
