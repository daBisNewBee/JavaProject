public class SingleTon {

    private static class Holder{
        private static final SingleTon instance = new SingleTon();
    }

    public static SingleTon getInstance(String param){
        return Holder.instance;
    }

    private SingleTon(){
        System.out.println("SingleTon constructor");
    }

}
