package hdzi.simpleremoteinvocation;

import hdzi.simpleremoteinvocation.client.RemoteInvocationBeanDefinitionRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Import(RemoteInvocationBeanDefinitionRegistrar.class)
public class SimpleRemoteInvocationApplication {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HttpHeaders defaultHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleRemoteInvocationApplication.class, args);
    }

}
