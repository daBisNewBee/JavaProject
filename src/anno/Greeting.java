package anno;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME) // 支持反射获取属性，需要设置此项
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
