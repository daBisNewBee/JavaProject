package tools;

import utils.Log;

import java.util.ArrayList;
import java.util.List;

public class MultiThreadList {

    private static final List<String> list = new ArrayList<>();

    public static void main(String[] args) {

        new Thread(()->{
            for (int i = 0; i <10; i++) {
                list.add(""+i);
//                Log.v("========:  "+i);
            }
        }).start();


        new Thread(()->{

            for (int i = 0; i < 10; i++) {

                while (true){

                    if (list.contains(""+i)) {
                        list.remove(""+i);
                        Log.v("remove:"+i);
                        break;
                    }
                    else{
                        Log.v("wait......");
                        continue ;
                    }
                }
//                list.add(""+i);
                Log.v(i);
            }
        }).start();

        Log.v("===========");
        list.forEach(Log::v);
    }
}
