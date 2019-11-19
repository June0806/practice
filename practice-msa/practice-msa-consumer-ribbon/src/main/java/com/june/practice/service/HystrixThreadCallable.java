package com.june.practice.service;

import com.june.practice.utils.HystrixThreadLocal;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.Callable;

public class HystrixThreadCallable<S> implements Callable<S> {
    private final RequestAttributes requestAttributes;
    private final Callable<S> callable;
    private String params;

    public HystrixThreadCallable(RequestAttributes requestAttributes, Callable<S> callable, String params) {
        this.requestAttributes = requestAttributes;
        this.callable = callable;
        this.params = params;
    }

    /**
     * 调用之前调用
     *
     * @return
     * @throws Exception
     */
    @Override
    public S call() throws Exception {
        try {
            //这里已属于另外一个线程
            RequestContextHolder.setRequestAttributes(requestAttributes);
            HystrixThreadLocal.ThreadLocal.set(params);
            return this.callable.call();
        } finally {
            RequestContextHolder.resetRequestAttributes();
            HystrixThreadLocal.ThreadLocal.remove();
        }
    }
}
