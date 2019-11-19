package com.june.practice.config;

import com.june.practice.service.FeignHystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HystrixConfiguration {

    @Bean
    public HystrixConcurrencyStrategy hystrixConcurrencyStrategy() {
        return new FeignHystrixConcurrencyStrategy();
    }
}
