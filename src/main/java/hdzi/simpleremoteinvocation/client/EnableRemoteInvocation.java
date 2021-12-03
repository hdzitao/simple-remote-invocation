package hdzi.simpleremoteinvocation.client;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启动注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(RemoteInvocationBeanDefinitionRegistrar.class)
public @interface EnableRemoteInvocation {
}
