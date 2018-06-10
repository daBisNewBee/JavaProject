package jvm.gc;

/**
 *
 * https://www.zhihu.com/question/21539353
 *
 * ���ü����㷨��ȱ�㣺
 *  ѭ������
 *
 * �����
 *  �ڴ�й©
 *
 * �����
 *  �ɴ����㷨
 *
 *
 *
 */
public class GCTest {

    static class GCObject{
        public Object instance = null;
    }

    public static void main(String[] args) {


        // GcObjectʵ��1�����ü�����1��ʵ��1�����ü���=1��
        GCObject gcObject1 = new GCObject();

        // GcObjectʵ��2�����ü�����1��ʵ��2�����ü���=1��
        GCObject gcObject2 = new GCObject();

        // GcObjectʵ��2�����ü����ټ�1��ʵ��2�����ü���=2��
        gcObject1.instance = gcObject2;

        // GcObjectʵ��1�����ü����ټ�1��ʵ��1�����ü���=2��
        gcObject2.instance = gcObject1;

        // ջ֡��obj1����ָ��Java�ѣ�GcObjectʵ��1�����ü�����1�����Ϊ1��
        gcObject1 = null;

        // ջ֡��obj2����ָ��Java�ѣ�GcObjectʵ��2�����ü�����1�����Ϊ1��
        gcObject2 = null;
    }
}
