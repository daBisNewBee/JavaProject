import jvm.classLoader.MyBean;

public class Test {

//    public int count = 0;
    public volatile int count = 0;

    public void increase(){
        count++;
    }

    public static void main(String[] args){

        final Test var = new Test();

        for (int i=0;i<10;i++){
            new Thread(){
                public void run(){
                    for (int j = 0;j<1000;j++){
                        var.increase();
                    }
                }
            }.start();
        }

        while (Thread.activeCount()>1){
            Thread.yield();
        }

        System.out.println(var.count);
    }

}
