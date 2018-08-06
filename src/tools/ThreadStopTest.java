package tools;

import java.util.concurrent.TimeUnit;

/**
 *
 * �򵥷�����
 *
 * ʹ��interrupt�������ն��߳̿ɷ�Ϊ���������

 ��1���̴߳�������״̬����ʹ����sleep������

 ��2��ʹ��while����isInterrupted������{����}���ж��߳��Ƿ��жϡ�
 *
 * ���ӷ�����
 *
 * ���� flag��ֹͣ�̷߳�ʽ�����ﲻ��˵��
 *
 *  ʹ��interrupt() ֹͣ�̵߳ļ�������
 *
 *  �ο�sleepThread������busyThread��
 *
 *  ԭ��
 *      ��Java��API�п��Կ�������������׳�InterruptedException�ķ���������Sleep
 *
 *  ���̣�
 *      1. sleepThread.interrupt();
 *      2. JVM ���жϱ�־λ�����
 *      3. Java API �׳� "InterruptedException"
 *      4. ��λ��־λ��Thread.currentThread().interrupt();
 *      5. �ж�isInterrupted()�������߳�
 *
 *
 *  https://blog.csdn.net/zbw18297786698/article/details/53432879
 *
 */
public class ThreadStopTest {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("ThreadStopTest.main ----> "+Thread.currentThread().isInterrupted());
        System.out.println("ThreadStopTest.main ----> "+Thread.currentThread().isInterrupted());
//        Thread.currentThread().interrupt();
        // ע�⣺ "interrupted()" ��������
        System.out.println("AFTER ThreadStopTest.main ----> "+Thread.currentThread().isInterrupted());
        System.out.println("AFTER ThreadStopTest.main ----> "+Thread.currentThread().isInterrupted());


        Thread sleepThread = new Thread(()->{
            while (true){
                try {

                    // 4. �ж��жϱ�־λ�������жϣ��˳���ǰ�߳�
                    if (Thread.currentThread().isInterrupted()){
                        System.out.println("isInterrupted. true. break.");
                        break;
                    }

                    // 2. ��Ӧ�жϣ�sleepThread.interrupt()
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("e = " + e.getMessage());
                    // Thread.sleep()���������ж��׳��쳣��
                    // Java��������Ƚ����̵߳��жϱ�ʶλ�����Ȼ���׳�InterruptedException��
                    // ��Ϊ�ڷ���InterruptedException�쳣��ʱ�򣬻�����жϱ��
                    // ������Ӵ�����ô��һ��ѭ����ʼ��ʱ�򣬾��޷���������쳣��
                    // �����쳣�����У��ٴ������жϱ��λ
                    // 3. ���жϱ�־λ���и�λ����Ȼʼ����false
                    break;
//                    Thread.currentThread().interrupt();

                    // ע�⣺�������ֱ��break����λ�ķ��������ˣ�
                }
            }
        });
        Thread busyThread = new Thread(()->{
            while (true){
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("busyThread isInterrupted true.");
                    break;
                }else {
//                    System.out.println("========");
                }
            }
        });
        sleepThread.start();
        busyThread.start();
        TimeUnit.SECONDS.sleep(2);
//        System.out.println("sleepThread = " + sleepThread.isInterrupted());
//        System.out.println("busyThread = " + busyThread.isInterrupted());

        /*
        * sleepThread �ܹ���Ӧ interrupt���ж�
        * busyThread ���ܣ���Ϊ��sleep���޷��׳�"InterruptedException"
        *
        * */
        // 1. �����ж�
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("sleepThread = " + sleepThread.isInterrupted());
        System.out.println("busyThread = " + busyThread.isInterrupted());
        TimeUnit.SECONDS.sleep(2);

    }
}
