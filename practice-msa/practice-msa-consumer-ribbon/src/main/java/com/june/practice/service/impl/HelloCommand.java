package com.june.practice.service.impl;

import com.june.practice.utils.Constants;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.springframework.web.client.RestTemplate;

/**
 * hello 服务 command
 */
public class HelloCommand extends HystrixCommand<String> {
    private static HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("helloServiceCommandKey");
    private RestTemplate restTemplate;
    private Long id;

    protected HelloCommand(RestTemplate template, Long id) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("helloService")).andCommandKey(commandKey));
        this.restTemplate = template;
        this.id = id;
    }

    /**
     * 刷新缓存，在写数据库时，需要更新缓存
     */
    public static void flushCache() {
        HystrixRequestCache.getInstance(commandKey, HystrixConcurrencyStrategyDefault.getInstance()).clear("helloIndexKey");
    }

    @Override
    protected String run() throws Exception {
        String body = restTemplate.getForEntity(Constants.HELLO_SERVICE_URL + "/index", String.class).getBody();
        return body;
    }

    @Override
    protected String getFallback() {
        return "服务异常，请稍后再试";
    }

    //开启缓存
    @Override
    protected String getCacheKey() {
        return "helloIndexKey";
    }
}
