package hdzi.simpleremoteinvocation.client.bean;

import com.alibaba.fastjson.JSONArray;
import lombok.Builder;
import lombok.Data;

/**
 * 远程调用参数
 */
@Data
@Builder
public class RIClientVO {
    // 调用的类
    private String clazz;
    // bean名字
    private String qualifier;
    // 调用的方法
    private String method;
    // 参数类
    private String[] argTypes;
    // 参数
    private JSONArray args;
}
