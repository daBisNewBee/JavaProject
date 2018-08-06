package anno;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME) // ֧�ַ����ȡ���ԣ���Ҫ���ô���
@Target({ElementType.METHOD,ElementType.CONSTRUCTOR})
@Documented
public @interface Greeting {

    enum FontColor{
        BLUE,
        RED,
        GREEN,
    };

    String name();

    FontColor fontColor() default FontColor.RED;

    int colorId() default -1;

}
