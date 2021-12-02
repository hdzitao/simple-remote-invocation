package hdzi.simpleremoteinvocation.test;

import hdzi.simpleremoteinvocation.client.RemoteType;
import lombok.Data;

@Data
@RemoteType("hdzi.simpleremoteinvocation.test.TestServerVO")
public class TestClientVO {
    private int[][] i = new int[][]{{1, 2, 3}, {4, 5, 6}};
}
