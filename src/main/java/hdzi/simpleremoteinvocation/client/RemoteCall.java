package hdzi.simpleremoteinvocation.client;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 远程配置
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RemoteCall {
    String clazz() default "";

    String qualifier() default "";

    String method() default "";
}
