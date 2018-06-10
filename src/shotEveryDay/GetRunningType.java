package shotEveryDay;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * ���������ʱ��ȡ�������ķ��Ͳ������� ?
 *
 *
 *
 */
public class GetRunningType {

    public static void main(String[] args) {

        Class c1  = new ArrayList<String>().getClass();
        Class c2  = new ArrayList<Integer>().getClass();
        System.out.println("c1 == c2:"+(c1 == c2));
        // c1 == c2:true    �����ڱ���ʱ����������ArrayList

        // ���ƣ�
        Map<String,Integer> map1 = new HashMap<String,Integer>();
        Map<String,String> map2 = new HashMap<String,String>();
        System.out.println("map2 == map1 " + (map1.getClass() == map2.getClass()));
        // map2 == map1 true

//        Map<String,Integer> map = new HashMap<String,Integer>();
//         �����ŷǳ���Ҫ���൱�������ڲ��࣬һ��Ҫ�����ڲ��࣡�������ܱ������ȡ��
        Map<String,Integer> map = new HashMap<String,Integer>(){};

        Type type = map.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = ParameterizedType.class.cast(type);
        for (Type typeArgument :
                parameterizedType.getActualTypeArguments()) {
            System.out.println("typeArgument = " + typeArgument.getTypeName());
            /*
            * typeArgument = java.lang.String
              typeArgument = java.lang.Integer
            *
            * ��ȡ��"{}"
            * ��
            * typeArgument = K
              typeArgument = V
            *
            * �������
            *
            * */
        }

    }

}
