package hdzi.simpleremoteinvocation.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.util.TypeUtils;
import hdzi.simpleremoteinvocation.client.bean.RIClientResult;
import hdzi.simpleremoteinvocation.client.bean.RIClientVO;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 远程调用interface对应的bean
 * 是个FactoryBean,生成代理类
 *
 * @param <T>
 */
@Log4j2
public class RemoteInvocationFactoryBean<T> implements FactoryBean<T> {
    @Value("${remote-invocation-server}")
    private String url;

    @Resource
    private RestTemplate restTemplate;

    private final Class<T> _interface;

    public RemoteInvocationFactoryBean(Class<T> _interface) {
        this._interface = _interface;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getObject() throws Exception {
        return (T) Proxy.newProxyInstance(_interface.getClassLoader(), new Class[]{_interface}, new RemoteCallInvocation());
    }

    @Override
    public Class<?> getObjectType() {
        return _interface;
    }


    private class RemoteCallInvocation implements InvocationHandler {
        private final String defaultCallClass;
        private final String defaultCallQualifier;

        {
            RemoteInvocationClient remoteCallService = _interface.getAnnotation(RemoteInvocationClient.class);
            defaultCallClass = remoteCallService.clazz();
            defaultCallQualifier = remoteCallService.qualifier();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            RemoteInvocation remoteCall = method.getAnnotation(RemoteInvocation.class);
            String callClass/*类*/, qualifier/*bean的名字*/, callMethod/*方法*/;
            if (remoteCall == null) { // 没有RemoteCall注解,类和方法使用默认
                callClass = defaultCallClass;
                qualifier = defaultCallQualifier;
                callMethod = method.getName();
            } else {
                // 类
                callClass = remoteCall.clazz();
                if (StringUtils.isEmpty(callClass)) {
                    // 默认RemoteCallService配置的类,也可以单独配置
                    callClass = defaultCallClass;
                }
                // 明确beanName
                qualifier = remoteCall.qualifier();
                if (StringUtils.isEmpty(callClass)) {
                    // 默认RemoteCallService配置的bean name,也可以单独配置
                    qualifier = defaultCallQualifier;
                }
                // 方法
                callMethod = remoteCall.method();
                if (StringUtils.isEmpty(callMethod)) {
                    // 默认相同方法名,也可以单独配置
                    callMethod = method.getName();
                }
            }
            Assert.isTrue(StringUtils.isNotEmpty(callClass), "调用类不能为空");
            Assert.isTrue(StringUtils.isNotEmpty(callMethod), "调用方法不能为空");
            // 参数类型
            String[] paramTypes = Arrays.stream(method.getParameterTypes()).map(type -> {
                if (type.isArray()) {
                    // 数组判断 ComponentType
                    Class<?> componentType = type.getComponentType();
                    StringBuilder builder = new StringBuilder("[");
                    while (componentType.isArray()) {
                        builder.append("[");
                        componentType = componentType.getComponentType();
                    }
                    builder.append("L");
                    RemoteType remoteCallType = componentType.getAnnotation(RemoteType.class);
                    builder.append(remoteCallType != null ? remoteCallType.value() : componentType.getName());
                    builder.append(";");
                    return builder.toString();
                } else {
                    RemoteType remoteCallType = type.getAnnotation(RemoteType.class);
                    return remoteCallType != null ? remoteCallType.value() : type.getName();
                }
            }).toArray(String[]::new);

            // 组装参数
            RIClientVO callVO = RIClientVO.builder()
                    .clazz(callClass)
                    .qualifier(qualifier)
                    .method(callMethod)
                    .argTypes(paramTypes)
                    .args((JSONArray) JSON.toJSON(args))
                    .build();
            // 调用
            String params = JSON.toJSONString(callVO);
            log.info("远程调用 {} {}", url, params);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            RIClientResult result = restTemplate.postForObject(url, new HttpEntity<>(params, headers), RIClientResult.class);
            log.info("远程调用返回 {} {} {}", url, params, JSON.toJSONString(result));
            // 转化返回结果返回
            if (result != null && result.isSuccess()) {
                return TypeUtils.cast(result.getResult(), method.getReturnType(), null);
            } else {
                throw new RuntimeException("远程调用失败");
            }
        }
    }
}
