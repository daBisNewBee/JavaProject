package anno;

import utils.Log;

import java.lang.reflect.Method;

public class AnnotationTest {

    @NewAnnotation("mainMethod")
    public static void main(String[] args) throws Exception {
        saying();
        sayHelloWithDefaultFontColor();
        sayHelloWithRedFontColor();


//        Class clzz = Class.forName("anno.AnnotationTest");
        Class clzz = new AnnotationTest().getClass();


        Method[] declaredMethod = clzz.getDeclaredMethods();//声明方法，所有
        Method[] methods = clzz.getMethods();// 公有方法

//        String methodName = "sayHelloWithDefaultFontColor";
        String methodName = "testAnno2";
        Method method = Class.forName("anno.AnnotationTest").getDeclaredMethod(methodName);
        Greeting greeting = method.getAnnotation(Greeting.class);

        Log.v(greeting.name());
        Log.v(greeting.fontColor().toString());
        Log.v(greeting.colorId());

    }


    @NewAnnotation(value = "sayMethod")
    public static void saying() {

    }

    @Greeting(name = "defaultfontcolor")
    static void sayHelloWithDefaultFontColor(){

    }

    @Greeting(name = "notdefault",fontColor = Greeting.FontColor.BLUE)
    public static void sayHelloWithRedFontColor(){

    }

    @Greeting(name = "name1",fontColor = Greeting.FontColor.BLUE,colorId = 1)
    public static void testAnno1(){

    }

    @Greeting(name = "name2",fontColor = Greeting.FontColor.GREEN,colorId = 2)
    public static void testAnno2(){

    }

}
