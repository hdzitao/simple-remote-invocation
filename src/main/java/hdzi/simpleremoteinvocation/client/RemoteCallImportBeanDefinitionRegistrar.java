package hdzi.simpleremoteinvocation.client;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * RemoteCallService BeanDefinition 注册器
 * 主要调用RemoteClassClassPathBeanDefinitionScanner进行BeanDefinition注册
 * <p>
 * 使用: 在主类上  @Import(RemoteCallImportBeanDefinitionRegistrar.class)
 */
public class RemoteCallImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, BeanFactoryAware, ResourceLoaderAware {
    private BeanFactory beanFactory; // BeanFactory可以获取扫描包
    private ResourceLoader resourceLoader; // ResourceLoader资源加载器

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        ClassPathBeanDefinitionScanner scanner = new RemoteCallClassPathBeanDefinitionScanner(registry);

        scanner.addIncludeFilter(new AnnotationTypeFilter(RemoteCallService.class)); // 过滤出 @RemoteCallService
        scanner.setResourceLoader(this.resourceLoader); // 资源加载器
        List<String> basePackages = AutoConfigurationPackages.get(this.beanFactory); // 扫描包
        scanner.scan(StringUtils.toStringArray(basePackages)/* list -> array */); // 启动扫描
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
