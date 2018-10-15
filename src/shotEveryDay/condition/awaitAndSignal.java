package shotEveryDay.condition;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * https://mp.weixin.qq.com/s?__biz=MzI3ODc3NzQ4NQ==&mid=2247484371&idx=1&sn=96e342cc909dc8d6b1303e3cbb6812e4&chksm=eb509831dc271127f6d20c47ee463e7ce6e3e5ae742e20b9acf751588688969dd23ff2d17f14&scene=38#wechat_redirect
 *
 *  Java ������ʽЭ�� Condition
 *              await��signal��signalAll ��Ҫ����ʽ�� Lock ���ʹ�ã�Lock.newCondition()��
 *
 *       ��ͨ����Э��
 *              wait��notify��notifyAll ��Ҫ�� synchronized ���ʹ��
 *
 *   await �ṩ�˱� wait ����ǿ��Ļ��ƣ�Ʃ���ṩ�˿��жϻ��߲����жϵ� await ���Ƶ�
 *
 *   ʹ�ö����������������������ѵ��¾����Ӷ�����Ч��
 *
 * Conditon�е�await()��ӦObject��wait()��
 Condition�е�signal()��ӦObject��notify()��
 Condition�е�signalAll()��ӦObject��notifyAll()��
 *
 *
 */
public class awaitAndSignal {

    private int queueSize = 10;

    private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);

    private Lock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();

    private Condition notEmpty = lock.newCondition();

    public static void main(String[] args)  {

        awaitAndSignal test = new awaitAndSignal();

        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();

        producer.start();
        consumer.start();
    }

    class Consumer extends Thread{

        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while(true){
                lock.lock();
                try {
                    while(queue.size() == 0){
                        try {
                            System.out.println("���пգ��ȴ�����");
                            /*
                            * �ؼ���
                            *
                            * �� wait һ����await �ڽ���ȴ����к���ͷ����� cpu��
                            * ���������̻߳��ѻ��߳�ʱ���жϺ���Ҫ���»�ȡ����
                            * ��ȡ����Ż�� await �������˳�
                            * */
                            notEmpty.await();
                            System.out.println("Consumer.await end ===== ");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.poll();                //ÿ�����߶���Ԫ��
                    notFull.signal();
                    System.out.println("�Ӷ���ȡ��һ��Ԫ�أ�����ʣ��"+queue.size()+"��Ԫ��");
                } finally{
                    System.out.println("Consumer.unlock");
                    lock.unlock();
                }
            }
        }
    }

    class Producer extends Thread{

        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while(true){
                lock.lock();
                try {
                    while(queue.size() == queueSize){
                        try {
                            System.out.println("���������ȴ��п���ռ�");
                            notFull.await();
                            System.out.println("Producer.await end =====");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.offer(1);        //ÿ�β���һ��Ԫ��
                    notEmpty.signal();
                    System.out.println("�����ȡ�в���һ��Ԫ�أ�����ʣ��ռ䣺"+(queueSize-queue.size()));
                } finally{
                    System.out.println("Producer.unlock");
                    lock.unlock();
                }
            }
        }
    }

}
