package shotEveryDay;

import utils.Log;

/**
 *
 * https://mp.weixin.qq.com/s?__biz=MzI3ODc3NzQ4NQ==&mid=2247484357&idx=1&sn=0d9a5e45603c721c6df2271b10a2a892&chksm=eb509827dc271131f6b9e02a64010828d59c5321ee31ba29c48a8aff264887fce9f7163133df&scene=21#wechat_redirect
 *
 * volatile ���ó�����
 *
 * 1. �̵߳��жϱ�־λ��
 * 2. ��ָֹ�����ţ�Javaʹ��double check��˫�ؼ�飩ʵ�ֵ���ģʽʱ��
 *      ��������Ҫʹ��volatile����(ָ������������ԣ���ָֹ������)
 *
 */
public class VolatileTest {

    volatile static int index = 0;

    volatile static boolean stop = true;

    public static void main(String[] args){

        // ����1��Ϊʲô���ֳ��������� volatile��
        /*
        * �Ա�����д�벻�����䵱ǰֵ
        *
        * ���㣺 boolean����¼�¶ȱ仯�ı�����
        * �����㣺 i++�� i = i*5;
        *
        * */
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
                        // �˴�����ʹ��"synchronized"!
                        // ������������ԭ�Ӳ�����volatile �ǲ��ܱ�֤ԭ���Ե�
                        synchronized (VolatileTest.class){
                            index++;
                            // ʵ�ʵĲ���Ϊ��1. �������ȡindex 2. index++ 3. indexд������
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
