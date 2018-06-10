package shotEveryDay.condition;

import java.util.PriorityQueue;
import java.util.Queue;

public class waitAndNotify {

    public static int queueSize = 10;

    public static Queue<Integer> queue = new PriorityQueue<>(queueSize);

    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        producer.start();
        consumer.start();
    }

    static class Consumer extends Thread{
        public void run(){
            consume();
        }

        private void consume(){
            while (true){
                synchronized (queue){
                    while (queue.size() == 0){
                        try{
                            System.out.println("queue is null, waiting for data ---- Consumer");
                            queue.wait();
                            System.out.println("queue is null, wait end ---- Consumer");
                        }catch (InterruptedException e){
                            e.printStackTrace();
                            System.out.println("Consumer InterruptedException");
                            queue.notify();
                        }
                    }

                    queue.poll();
                    queue.notify();
                    System.out.println("remove 1 element, left " + queue.size()+ " elements");
                }
            }
        }
    }

    static class Producer extends Thread{
        public void run(){
            produce();
        }

        private void produce(){
            while (true){
                synchronized (queue){
                    while (queue.size() == queueSize){
                        try{
                            System.out.println("queue is full, waiting for more space ---- Producer");
                            queue.wait();
                            System.out.println("queue is full, wait end ---- Producer");
                        }catch (InterruptedException e){
                            e.printStackTrace();
                            System.out.println("Producer  InterruptedException");
                            queue.notify();
                        }
                    }

                    queue.offer(1);
                    queue.notify();
                    System.out.println("insert 1 element, left " + (queueSize - queue.size()) + " spaces");
                }
            }
        }
    }
}
