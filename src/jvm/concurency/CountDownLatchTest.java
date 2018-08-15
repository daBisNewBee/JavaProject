package jvm.concurency;

import java.util.concurrent.CountDownLatch;

/**
 *
 * C �ȴ� A��B������C��ִ�С�
 *
 * "CountDownLatch һ������ĳ���߳� A �ȴ����ɸ������߳�ִ��������֮������ִ�С�"
 *
 * �޷����ã�
 *
 */
public class CountDownLatchTest {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        for (int i = 0; i < 2; i++) {

            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName()+" ����ִ�С�����");
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+" ִ����ϡ�����");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }).start();
        }

        try {
            System.out.println("�ȴ�2�����߳�ִ�����...");
            countDownLatch.await();
            System.out.println("2�����߳��Ѿ�ִ�����");
            System.out.println("����ִ�����߳�");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
