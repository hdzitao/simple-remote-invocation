package hdzi.simpleremoteinvocation.test;

import hdzi.simpleremoteinvocation.client.RemoteInvocationClient;

import java.util.List;

@RemoteInvocationClient(clazz = "hdzi.simpleremoteinvocation.test.TestServiceImpl")
public interface TestService {
    void testVoid();

    int testPrimitive(int i);

    int[] testPrimitiveArray(int[] i);

    int[][] testPrimitiveArray2(int[][] i);

    TestServerVO testRowObject(TestServerVO vo);

    TestClientVO testObject(TestClientVO vo);

    TestClientVO[] testObjectArray(TestClientVO[] vo);

    TestClientVO[][] testObjectArray2(TestClientVO[][] vo);

    List<TestClientVO> testObjectList(List<TestClientVO> vo);

    Class<?> testClass(Class<?> clazz);
}
