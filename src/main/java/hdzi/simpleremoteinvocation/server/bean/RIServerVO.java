package hdzi.simpleremoteinvocation.server.bean;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

@Data
public class RIServerVO {
    private Class<?> clazz;
    private String qualifier;
    private String method;
    private Class<?>[] argTypes;

    private JSONArray args;
}
