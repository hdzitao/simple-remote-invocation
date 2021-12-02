package hdzi.simpleremoteinvocation.test;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl {
    public void testVoid() {
    }

    public int testPrimitive(int i) {
        return i;
    }

    public int[] testPrimitiveArray(int[] i) {
        return i;
    }

    public int[][] testPrimitiveArray2(int[][] i) {
        return i;
    }

    public TestServerVO testRowObject(TestServerVO vo) {
        return vo;
    }

    public TestServerVO testObject(TestServerVO vo) {
        return vo;
    }

    public TestServerVO[] testObjectArray(TestServerVO[] vo) {
        return vo;
    }

    public TestServerVO[][] testObjectArray2(TestServerVO[][] vo) {
        return vo;
    }

    public List<TestServerVO> testObjectList(List<TestServerVO> vo) {
        return vo;
    }

    public Class<?> testClass(Class<?> clazz) {
        return clazz;
    }
}
