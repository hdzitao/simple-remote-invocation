package hdzi.simpleremoteinvocation.test;

import hdzi.simpleremoteinvocation.client.RemoteInvocationClient;

@RemoteInvocationClient(clazz = "hdzi.simpleremoteinvocation.test.TestServiceImpl")
public interface TestService {
    int test();
}
