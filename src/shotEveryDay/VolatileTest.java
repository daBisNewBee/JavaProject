package shotEveryDay;

import utils.Log;

/**
 *
 * https://mp.weixin.qq.com/s?__biz=MzI3ODc3NzQ4NQ==&mid=2247484357&idx=1&sn=0d9a5e45603c721c6df2271b10a2a892&chksm=eb509827dc271131f6b9e02a64010828d59c5321ee31ba29c48a8aff264887fce9f7163133df&scene=21#wechat_redirect
 *
 * volatile 常用场景：
 *
 * 1. 线程的中断标志位。
 * 2. 防止指令重排：Java使用double check（双重检查）实现单例模式时，
 *      单例变量要使用volatile修饰(指令操作的有序性，防止指令重排)
 *
 */
public class VolatileTest {

    volatile static int index = 0;

    volatile static boolean stop = true;

    public static void main(String[] args){

        // 场景1：为什么此种场景可以用 volatile？
        /*
        * 对变量的写入不依赖其当前值
        *
        * 满足： boolean、记录温度变化的变量等
        * 不满足： i++、 i = i*5;
        *
        * */
        // 线程1
        while (!stop){
            // do sth
        }

        // 线程2
        stop = true;
        // 当线程2修改 stop 变量值时会导致线程1的工作内存中
        // stop 缓存失效进而主动去主存中重新读取 stop 值。


        for (int i = 0;i<100;i++)
        {
            new Thread(){
                @Override
                public void run() {
                    for (int j=0;j<1000;j++)
                        // 此处必须使用"synchronized"!
                        // 自增操作不是原子操作，volatile 是不能保证原子性的
                        synchronized (VolatileTest.class){
                            index++;
                            // 实际的步骤为：1. 从主存读取index 2. index++ 3. index写入主存
                        }
                }
            }.start();
        }

        int count = 0;
        while ((count = Thread.activeCount())>1){
            System.out.println("count = " + count);
            Thread.yield();
        }

        Log.v("END--------> index:"+index);


    }

}
