package hdzi.simpleremoteinvocation.client;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 两端的实体对象路径不一样,需要手工配置
 * qt端的po路径
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RemoteCallType {
    String value();
}
