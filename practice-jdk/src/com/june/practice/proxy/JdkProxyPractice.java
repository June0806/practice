package com.june.practice.proxy;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理实现类
 */
public class JdkProxyPractice implements InvocationHandler {
    private Object instance;

    public JdkProxyPractice(Object target) {
        this.instance = target;
    }

    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(instance.getClass().getClassLoader(), instance.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理 执行前");
        Object invoke = method.invoke(instance, args);
        System.out.println("动态代理 执行后");
        return invoke;
    }


    public static class JdkProxyPracticeUnitTest {
        @Test
        public void test(){
            ProxyPractice proxyPractice = new ProxyPracticeImpl();
            JdkProxyPractice jdkProxyPractice = new JdkProxyPractice(proxyPractice);
            ProxyPractice proxy = (ProxyPractice) jdkProxyPractice.getProxy();
            proxy.add(10, 20);
        }
    }
}
