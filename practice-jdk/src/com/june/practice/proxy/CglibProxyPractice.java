package com.june.practice.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * cglib 实现的动态代理
 */
public class CglibProxyPractice implements MethodInterceptor {

    public <T> T getProxy(Class<T> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        Object o = enhancer.create();
        return (T) o;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib 调用前");
        Object invoke = methodProxy.invokeSuper(o, objects);
        System.out.println("cglib 调用后");
        return invoke;
    }

    public static class CglibProxyPracticeUnitTest {
        @Test
        public void test() {
            ProxyPracticeImpl proxy = new CglibProxyPractice().getProxy(ProxyPracticeImpl.class);
            proxy.add(10, 20);
        }
    }
}
