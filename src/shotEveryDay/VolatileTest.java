package shotEveryDay;

import utils.Log;

/**
 *
 * https://mp.weixin.qq.com/s?__biz=MzI3ODc3NzQ4NQ==&mid=2247484357&idx=1&sn=0d9a5e45603c721c6df2271b10a2a892&chksm=eb509827dc271131f6b9e02a64010828d59c5321ee31ba29c48a8aff264887fce9f7163133df&scene=21#wechat_redirect
 *
 * volatile ���ó�����
 *
 * 1. �̵߳��жϱ�־λ��
 * 2. ��Javaʹ��double check��˫�ؼ�飩ʵ�ֵ���ģʽʱ��
 *      ��������Ҫʹ��volatile����(ָ������������ԣ���ָֹ������)
 *
 */
public class VolatileTest {

    volatile static int index = 0;

    volatile static boolean stop = false;

    public static void main(String[] args){

        // �߳�1
        while (!stop){
            // do sth
        }

        // �߳�2
        stop = true;
        // ���߳�2�޸� stop ����ֵʱ�ᵼ���߳�1�Ĺ����ڴ���
        // stop ����ʧЧ��������ȥ���������¶�ȡ stop ֵ��


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
