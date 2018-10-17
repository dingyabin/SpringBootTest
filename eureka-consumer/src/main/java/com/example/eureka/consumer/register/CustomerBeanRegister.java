package com.example.eureka.consumer.register;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by dingyabin001
 * Date: 2018/10/16.
 * Time:17:33
 */
@Component
public class CustomerBeanRegister implements BeanDefinitionRegistryPostProcessor {

    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    private String basePackages = "provider.common.api";


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false) {
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition definition) {
                return definition.getMetadata().isInterface();
            }
        };
        provider.addIncludeFilter((metadataReader, metadataReaderFactory) -> metadataReader.getClassMetadata().isInterface());
        for (String _package : basePackages.split(",")) {
            Set<BeanDefinition> components = provider.findCandidateComponents(_package.trim());
            for (BeanDefinition component : components) {
                GenericBeanDefinition beanDefinition = (GenericBeanDefinition) component;
                //需放在setBeanClass(ProxyBeanFactory.class)之前进行
                String beanName = beanNameGenerator.generateBeanName(beanDefinition, registry);

                ConstructorArgumentValues values = new ConstructorArgumentValues();
                values.addIndexedArgumentValue(0, beanDefinition.getBeanClassName());

                beanDefinition.setConstructorArgumentValues(values);
                //注意:ProxyBeanFactory实现了FactoryBean接口
                beanDefinition.setBeanClass(ProxyBeanFactory.class);

                registry.registerBeanDefinition(beanName, beanDefinition);
            }
        }
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }


}
