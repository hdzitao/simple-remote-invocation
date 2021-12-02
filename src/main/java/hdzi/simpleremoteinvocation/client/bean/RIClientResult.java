package hdzi.simpleremoteinvocation.client.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 远程调用结果
 */
@Data
@NoArgsConstructor
public class RIClientResult {
    // 调用是否成功
    private boolean success;

    // 结果
    private Object result;

    // 执行的参数
    private String method;
}
