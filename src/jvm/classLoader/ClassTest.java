package jvm.classLoader;

import utils.Log;

import java.lang.reflect.Constructor;

public class ClassTest {

    public static void main(String[] args) throws Exception {


            Class strClz = Class.forName(String.class.getName());
            String tmp = (String) strClz.newInstance();
            Log.v("newInstance:"+tmp.toString());

            Constructor c1 = strClz.getDeclaredConstructor();
            c1.setAccessible(true);
            String str1 = (String)c1.newInstance();
            Log.v("str1:"+str1.toString());

            Constructor c2 = strClz.getDeclaredConstructor(new Class[]{String.class});
            c2.setAccessible(true);
            String str2 = (String)c2.newInstance("hello");
            Log.v("str2:"+str2.toString());


            // ��String.classΪ��
            String str = new String();
            Class cl = str.getClass();
            /**
             * ��ȡ����+����<br>
             * java.lang.String
             */
            cl.getName();
            /**
             * ֻ��ȡ���� - String
             */
            cl.getSimpleName();
            /**
             * ��ȡ�����Class����<br>
             * ��Ϊ���е�Java�඼�̳���Object,����Ҳһ��.�������鱾��Ҳ�Ǹ�Class, �õ������Class��ȻҲ����ת������.
             */
            cl.getComponentType();
            /**
             * ���ع���������,��������Ĺ��г�Ա.
             */
            cl.getConstructors();
            /**
             * ���ط�������,��������Ĺ��г�Ա.
             */
            cl.getMethods();
            /**
             * ����������,��������Ĺ��г�Ա.
             */
            cl.getFields();
            /**
             * ����ȫ������������,������public/private����protected,����������ĳ�Ա.
             */
            cl.getDeclaredConstructors();
            /**
             * ����ȫ��������,������public/private����protected,����������ĳ�Ա.
             */
            cl.getDeclaredFields();
            /**
             * ����ȫ����������,������public/private����protected,����������ĳ�Ա.
             */
            cl.getDeclaredMethods();
            /**
             * ��ȡ��ǰ�����η�
             */
            cl.getModifiers();
        }

}
