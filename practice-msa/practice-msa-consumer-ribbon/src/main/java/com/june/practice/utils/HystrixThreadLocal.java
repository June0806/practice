package com.june.practice.utils;

/**
 * 用来保存请求上下文数据
 */
public class HystrixThreadLocal {
    public static final ThreadLocal<String> ThreadLocal = new ThreadLocal<>();
}
