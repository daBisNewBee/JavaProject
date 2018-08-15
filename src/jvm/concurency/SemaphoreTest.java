package jvm.concurency;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    /*
    *
    * ͬʱֻ����5���̷߳��ʣ��ﵽ"����ĳ���������������ʵĸ���"��Ŀ�ģ�
    *
    * ��������
    *   8�����ˣ�ֻ��5̨��������
    *
    * */
    private static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                test();
            }).start();
        }
    }

    private static void test() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" enter ..... "+ Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(" exit ..... "+ Thread.currentThread().getName());
        semaphore.release();
    }
}
