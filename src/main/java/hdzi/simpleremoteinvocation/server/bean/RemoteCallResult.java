package hdzi.simpleremoteinvocation.server.bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RemoteCallResult {
    public static final RemoteCallResult FAIL = builder().success(false).build();

    // 调用是否成功
    private boolean success;

    // 结果
    private Object result;

    // 具体执行的方法
    private String method;
}
