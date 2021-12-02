package hdzi.simpleremoteinvocation.server.service.impl;

import com.alibaba.fastjson.JSONArray;
import hdzi.simpleremoteinvocation.server.bean.RIServerResult;
import hdzi.simpleremoteinvocation.server.bean.RIServerVO;
import hdzi.simpleremoteinvocation.server.service.RemoteInvocationService;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

@Service
public class RemoteInvocationServiceImpl implements RemoteInvocationService, ApplicationContextAware {
    private ApplicationContext context;

    @Override
    @SneakyThrows
    public RIServerResult call(RIServerVO vo) {
        // 找到相应的调用方法
        Method method = ReflectionUtils.findMethod(vo.getClazz(), vo.getMethod(), vo.getArgTypes());
        Assert.notNull(method, "找不到远程调用的方法");
        // 反射参数列表
        Type[] parameterTypes = method.getGenericParameterTypes();
        Object[] realArgs = new Object[parameterTypes.length];
        JSONArray args = vo.getArgs();
        for (int i = 0; i < parameterTypes.length; i++) {
            realArgs[i] = args.getObject(i, parameterTypes[i]);
        }

        // 获取bean
        String qualifier = vo.getQualifier();
        Object bean = StringUtils.isEmpty(qualifier) ? context.getBean(vo.getClazz()) : context.getBean(qualifier);
        // 调用
        Object invoke = method.invoke(bean, realArgs);

        // 返回
        return RIServerResult.builder()
                .success(true)
                .result(invoke)
                .method(method.toString())
                .build();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
