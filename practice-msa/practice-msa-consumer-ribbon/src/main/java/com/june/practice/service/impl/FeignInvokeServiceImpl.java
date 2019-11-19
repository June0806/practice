package com.june.practice.service.impl;

import com.june.practice.service.FeignInvokeService;
import com.june.practice.service.FeignService;
import com.june.practice.utils.HystrixThreadLocal;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeignInvokeServiceImpl implements FeignInvokeService {
    @Autowired
    private FeignService feignService;

    @HystrixCommand
    @CacheResult
    @Override
    public String invoke(Integer userId, Integer a, Integer b) {
        System.out.println("current thread:" + Thread.currentThread().getId());
        System.out.println("thread local:" + HystrixThreadLocal.ThreadLocal.get());
        System.out.println("HystrixThreadLocal:" + HystrixThreadLocal.ThreadLocal.get());
        return feignService.invoke(a, b);
    }
}
