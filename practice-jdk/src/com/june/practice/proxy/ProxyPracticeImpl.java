package com.june.practice.proxy;

/**
 * 默认实现
 */
public class ProxyPracticeImpl implements ProxyPractice {
    @Override
    public String add(int paramA, int paramB) {
        System.out.println("默认实现 parameter a :" + paramA + " paramter b :" + paramB);
        return "默认实现";
    }
}
