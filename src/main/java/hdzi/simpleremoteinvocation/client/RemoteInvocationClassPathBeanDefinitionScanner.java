package hdzi.simpleremoteinvocation.client;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

/**
 * RemoteCallService扫描器
 */
public class RemoteInvocationClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {
    public RemoteInvocationClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        // 调用父类doScan方法扫描
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        for (BeanDefinitionHolder holder : beanDefinitionHolders) {
            processBeanDefinitions(holder); // 拿到BeanDefinition后进行处理
        }

        return beanDefinitionHolders;
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        // 默认不扫描 interface,需要重写扫描interface
        return beanDefinition.getMetadata().isInterface();
    }

    private void processBeanDefinitions(BeanDefinitionHolder holder) {
        GenericBeanDefinition beanDefinition = (GenericBeanDefinition) holder.getBeanDefinition();
        /*将bean的真实类型改变为FactoryBean,这要注入的时候调用RemoteCallFactoryBean.getObject拿到代理实现*/
        // 新增一个构造函数调用,传入interface
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanDefinition.getBeanClassName());
        // 换成FactoryBean
        beanDefinition.setBeanClass(RemoteInvocationFactoryBean.class);
    }
}
