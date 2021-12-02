package hdzi.simpleremoteinvocation.client;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 远程调用的server注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RemoteCallService {
    String clazz() default ""; // class名

    String qualifier() default ""; // bean的名字
}
