package com.june.practice.config;

import com.june.practice.filter.MyFilter;
import com.june.practice.filter.PostFilter;
import com.june.practice.filter.SecondPreFilter;
import com.june.practice.filter.ThirdPreFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfig {
    @Bean
    @ConditionalOnMissingBean(MyFilter.class)
    public MyFilter myZuul() {
        return new MyFilter();
    }

    @Bean
    @ConditionalOnMissingBean(SecondPreFilter.class)
    public SecondPreFilter secondPreFilter() {
        return new SecondPreFilter();
    }

    @Bean
    @ConditionalOnMissingBean(ThirdPreFilter.class)
    public ThirdPreFilter thirdPreFilter() {
        return new ThirdPreFilter();
    }

    @Bean
    @ConditionalOnMissingBean(PostFilter.class)
    public PostFilter postFilter() {
        return new PostFilter();
    }
}
