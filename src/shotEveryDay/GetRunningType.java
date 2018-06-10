package shotEveryDay;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 如何在运行时获取被擦除的泛型参数类型 ?
 *
 *
 *
 */
public class GetRunningType {

    public static void main(String[] args) {

        Class c1  = new ArrayList<String>().getClass();
        Class c2  = new ArrayList<Integer>().getClass();
        System.out.println("c1 == c2:"+(c1 == c2));
        // c1 == c2:true    泛型在编译时被擦出成了ArrayList

        // 类似：
        Map<String,Integer> map1 = new HashMap<String,Integer>();
        Map<String,String> map2 = new HashMap<String,String>();
        System.out.println("map2 == map1 " + (map1.getClass() == map2.getClass()));
        // map2 == map1 true

//        Map<String,Integer> map = new HashMap<String,Integer>();
//         大括号非常重要，相当于匿名内部类，一定要匿名内部类！！！才能被反射获取到
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
            * 若取消"{}"
            * 则：
            * typeArgument = K
              typeArgument = V
            *
            * 体会区别。
            *
            * */
        }

    }

}
