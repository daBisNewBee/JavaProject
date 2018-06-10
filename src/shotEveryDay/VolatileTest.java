package shotEveryDay;

import utils.Log;

/**
 *
 * https://mp.weixin.qq.com/s?__biz=MzI3ODc3NzQ4NQ==&mid=2247484357&idx=1&sn=0d9a5e45603c721c6df2271b10a2a892&chksm=eb509827dc271131f6b9e02a64010828d59c5321ee31ba29c48a8aff264887fce9f7163133df&scene=21#wechat_redirect
 *
 * volatile 常用场景：
 *
 * 1. 线程的中断标志位。
 * 2. ：Java使用double check（双重检查）实现单例模式时，
 *      单例变量要使用volatile修饰(指令操作的有序性，防止指令重排)
 *
 */
public class VolatileTest {

    volatile static int index = 0;

    volatile static boolean stop = false;

    public static void main(String[] args){

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
                        index++;
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
