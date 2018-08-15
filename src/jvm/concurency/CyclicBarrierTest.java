package jvm.concurency;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 * �ػ�դ����
 *
 * "�ػ�" ����Ϊ�����еȴ��̶߳����ͷ��Ժ�CyclicBarrier���Ա����ã�
 * "դ��"���ǵȴ���"����"����await���߳̾ʹ���barrier�ˡ�
 *
 * A��B��C "һ���߳�"����ȴ���ĳһ������Ȼ����ͳһִ�С�
 *
 * ������ "CountDownLatch"�� C �ȴ� A��B��
 *
 * CountDownLatch �ǲ��ܹ����õģ��� CyclicBarrier �ǿ������õġ�
 *
 * ���ڣ�
 *      ���ڶ��̼߳������ݣ����ϲ���������Ӧ�ó���
 *
 * �ο���
 *  https://mp.weixin.qq.com/s?__biz=MzI3ODc3NzQ4NQ==&mid=2247484869&idx=1&sn=d8b4816e7bc6d4438f56ee8127e717ea&chksm=eb509e27dc27173128c749ad24fe9d5eaf496a5e0c236594426340789e76ad166fa4320f7538&scene=38#wechat_redirect
 *
 */
public class CyclicBarrierTest {

    public static void main(String[] args) throws InterruptedException {
        int N = 4;

        // �˴��� Runnable Ԥ�������̵߳���barrier֮�󣬽��еĶ��������
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new Runnable() {
            @Override
            public void run() {
                System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName());
            }
        });

        for (int i = 0; i < N; i++) {
            new Writer(cyclicBarrier).start();
        }

        Thread.sleep(5000);

        System.out.println("CyclicBarrier����");

        for (int i = 0; i < N; i++) {
            new Writer(cyclicBarrier).start();
        }

    }

    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyc) {
            cyclicBarrier = cyc;
        }

        @Override
        public void run() {
            super.run();
            try {
                int wait = 2;
//                int wait = new Random().nextInt(5);
                System.out.println(Thread.currentThread().getName() + " ����д������....�ȴ���"+wait );
                Thread.sleep(wait * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "д���������....");

            try {
                // ��ʱ���ȵ���barrier״̬���߳���ִ�к�������
//                cyclicBarrier.await(2, TimeUnit.SECONDS);
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
//            } catch (TimeoutException e) {
//                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+" �����߳�д����ϣ�����������������...");

        }
    }

}
