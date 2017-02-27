package com.chao.helper.utils;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ByteArrayResource;

/**
 * @author  by barry-acer on 2016/3/3.
 */
public class SpringRegisterUtils {
    /**
     *
     * @param beanId
     * @param xmlConfig
     */
    public static void register(ApplicationContext applicationContext, String beanId, byte[] xmlConfig) {

        DefaultListableBeanFactory acf = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ByteArrayResource(xmlConfig));

        acf.registerBeanDefinition(beanId, factory.getMergedBeanDefinition(beanId));

    }
}
