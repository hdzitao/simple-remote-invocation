package hdzi.simpleremoteinvocation;

import hdzi.simpleremoteinvocation.test.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SimpleRemoteInvocationApplicationTests {

    @Resource
    private TestService testService;

    @Test
    void contextLoads() {
        System.out.println(testService.test());
    }

}
