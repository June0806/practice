package com.june.practice.proxy;

import org.junit.Test;

/**
 * 静态代理
 */
public class StaticProxyPractice implements ProxyPractice {
    private ProxyPractice instance;

    public StaticProxyPractice(ProxyPractice instance) {
        this.instance = instance;
    }

    @Override
    public String add(int paramA, int paramB) {
        System.out.println("代理实现 前");
        String response = instance.add(paramA, paramB);
        System.out.println("代理实现 后");
        return response;
    }

    public static class StaticProxyPracticeUnitTest {
        @Test
        public void test() {
            ProxyPractice proxyPractice = new ProxyPracticeImpl();
            StaticProxyPractice staticProxyPractice = new StaticProxyPractice(proxyPractice);
            staticProxyPractice.add(10, 20);
        }
    }
}
