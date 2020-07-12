package com.tutorialspoint;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring BeanFactory是一个最简单的容器，它主要的功能是为依赖注入 （DI） 提供支持，
 * 这个容器接口在 org.springframework.beans.factory.BeanFactor 中被定义。
 * BeanFactory 和相关的接口，比如BeanFactoryAware、DisposableBean、InitializingBean，
 * 仍旧保留在 Spring 中，主要目的是向后兼容已经存在的和那些 Spring 整合在一起的第三方框架。
 */
public class MainApp {
    public static void main(String[] args) {

        /*
         * 第一步利用框架提供的 XmlBeanFactory() API 去生成工厂 bean
         * 以及利用 ClassPathResource() API 去加载在路径 CLASSPATH 下可用的 bean 配置文件。
         * XmlBeanFactory() API 负责创建并初始化所有的对象，即在配置文件中提到的 bean。
         */
        /*
         * 在 Spring 中，有大量对 BeanFactory 接口的实现。
         * 其中，最常被使用的是 XmlBeanFactory 类。
         * 这个容器从一个 XML 文件中读取配置元数据，由这些元数据来生成一个被配置化的系统或者应用。
         */

//        XmlBeanFactory factory = new XmlBeanFactory
//                (new ClassPathResource("Beans.xml"));


        AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        /*
         * 第二步利用第一步生成的 bean 工厂对象的 getBean() 方法得到所需要的 bean。
         * 这个方法通过配置文件中的 bean ID 来返回一个真正的对象，该对象最后可以用于实际的对象。
         * 一旦得到这个对象，你就可以利用这个对象来调用任何方法。
         *
         */
        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");

        obj.getMessage();

        // set message, and the message would be changed
        obj.setMessage("Changed World!");
        // return: Changed World!
        obj.getMessage();

        /*
         * 在JVM中注册关闭 hook。这样做可以确保正常关闭，为了让所有的资源都被释放，可以在单个 beans 上调用 destroy 方法。
         *
         * 实现上需要注册一个在 AbstractApplicationContext 类中声明的关闭 hook 的 registerShutdownHook() 方法。
         * 它将确保正常关闭，并且调用相关的 destroy 方法。
         *
         * 一般需要销毁时添加
         */
        context.registerShutdownHook();
    }
}