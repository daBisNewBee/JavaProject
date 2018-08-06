package basic;

public class FanXingTest {

    static class Bean<T>{
        private T t;

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }

        public <T> T get(T t){
            return t;
        }
    }

    static class BeanEx{
        private String t;

        public String getT() {
            return t;
        }

        public void setT(String t) {
            this.t = t;
        }

        // ���ͷ����ı�ʶ����ʾ�÷����Ƿ��ͷ����������ڷǷ��͵��ࡣ
        public <T> T get(T t){
            return t;
        }
    }

    public static void main(String[] args) {
        Bean<String> bean = new Bean<String>();
        bean.setT("hello");
        String value = bean.getT();
        System.out.println("value = " + value);

        int ii = bean.get(123);
        System.out.println("ii = " + ii);

        BeanEx beanEx = new BeanEx();
        String va = beanEx.get("123");
        int i  = beanEx.get(123);
    }
}
