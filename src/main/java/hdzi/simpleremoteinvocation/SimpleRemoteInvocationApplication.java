package hdzi.simpleremoteinvocation;

import hdzi.simpleremoteinvocation.client.RemoteInvocationBeanDefinitionRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(RemoteInvocationBeanDefinitionRegistrar.class)
public class SimpleRemoteInvocationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleRemoteInvocationApplication.class, args);
    }

}
