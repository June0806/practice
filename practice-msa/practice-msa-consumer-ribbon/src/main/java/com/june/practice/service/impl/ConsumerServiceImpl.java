package com.june.practice.service.impl;

import com.june.practice.service.ConsumerService;
import com.june.practice.utils.Constants;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCollapser(batchMethod = "invokeHelloService", collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "10000")
    })
    public String invoke() {
        return null;
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD")
    },
            threadPoolProperties = {})
    @CacheResult
    @Override
    public String invokeHelloService() throws ExecutionException, InterruptedException {
        String body = restTemplate.getForEntity(Constants.HELLO_SERVICE_URL + "/index", String.class).getBody();
        return body;
//        Future<String> queue = new HelloCommand(restTemplate, 1L).queue();
//        HelloCommand.flushCache();
//        return queue.get();
    }
}
