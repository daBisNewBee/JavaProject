package basic.defalut;

public interface Animal {

    void speek();


    /*
    *
    * ���Ӿ���ķ����� ��Ϊ�˼��еĳ�ǧ�����Java�����������µĹ��ܣ�
    * �Ҳ��ض���Щ�����½�����ơ� ���磬 ֻ����Collection�ӿ���
        ����default Stream<E> stream(), ��Ӧ��Set��List�ӿ��Լ����ǵ�����
        �������˵ķ����� ����Ϊÿ�����඼����copy���������
    * */
    default void say(){
        System.out.println("this is in animal say.");
    };

}
