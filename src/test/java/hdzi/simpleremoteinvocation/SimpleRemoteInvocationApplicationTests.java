package hdzi.simpleremoteinvocation;

import hdzi.simpleremoteinvocation.test.TestClientVO;
import hdzi.simpleremoteinvocation.test.TestServerVO;
import hdzi.simpleremoteinvocation.test.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SimpleRemoteInvocationApplicationTests {

    @Resource
    private TestService testService;

    @Test
    void testVoid() {
        testService.testVoid();
    }

    @Test
    void testPrimitive() {
        int i = testService.testPrimitive(112233);
        assertEquals(112233, i);
    }

    @Test
    void testPrimitiveArray() {
        int[] ints = testService.testPrimitiveArray(new int[]{1, 2, 3, 4});
        assertArrayEquals(new int[]{1, 2, 3, 4}, ints);
    }

    @Test
    void testPrimitiveArray2() {
        int[][] ints = testService.testPrimitiveArray2(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}});
        assertArrayEquals(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}}, ints);
    }

    @Test
    void testRowObject() {
        TestServerVO vo = new TestServerVO();
        vo.setI(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}});
        TestServerVO vo2 = testService.testRowObject(vo);
        assertNotSame(vo, vo2);
        assertEquals(vo, vo2);
    }

    @Test
    void testObject() {
        TestClientVO vo = new TestClientVO();
        vo.setI(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}});
        TestClientVO vo2 = testService.testObject(vo);
        assertNotSame(vo, vo2);
        assertEquals(vo, vo2);
    }

    @Test
    void testObjectArray() {
        TestClientVO vo = new TestClientVO();
        vo.setI(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}});
        TestClientVO[] vo2 = testService.testObjectArray(new TestClientVO[]{vo});
        assertArrayEquals(new TestClientVO[]{vo}, vo2);
    }

    @Test
    void testObjectArray2() {
        TestClientVO vo = new TestClientVO();
        vo.setI(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}});
        TestClientVO[][] vo2 = testService.testObjectArray2(new TestClientVO[][]{{vo}, {vo}});
        assertArrayEquals(new TestClientVO[][]{{vo}, {vo}}, vo2);
    }

    @Test
    void testObjectList() {
        TestClientVO vo = new TestClientVO();
        vo.setI(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}});
        List<TestClientVO> vo2 = testService.testObjectList(Collections.singletonList(vo));
        assertEquals(Collections.singletonList(vo), vo2);
    }

    @Test
    void testClass() {
        Class<?> aClass = testService.testClass(int.class);
        assertEquals(int.class, aClass);

        aClass = testService.testClass(List.class);
        assertEquals(List.class, aClass);

        aClass = testService.testClass(TestClientVO.class);
        assertEquals(TestClientVO.class, aClass);
    }

}
