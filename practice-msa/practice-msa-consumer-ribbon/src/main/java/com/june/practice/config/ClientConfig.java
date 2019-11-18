package com.june.practice.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
/**
 * --------------------- ribbon 骨架 -------------------
 * IClientConfig {@link com.netflix.client.config.IClientConfig}-{@link com.netflix.client.config.DefaultClientConfigImpl}
 * IRule {@link com.netflix.loadbalancer.IRule}-{@link com.netflix.loadbalancer.ZoneAvoidanceRule}
 * IPing {@link com.netflix.loadbalancer.IPing}-{@link com.netflix.loadbalancer.DummyPing}
 * ServerList<Server>{@link com.netflix.loadbalancer.ServerList}-{@link com.netflix.loadbalancer.ConfigurationBasedServerList}
 * ServerListFilter<Server>{@link com.netflix.loadbalancer.ServerListFilter}-{@link org.springframework.cloud.netflix.ribbon.ZonePreferenceServerListFilter}
 * ILoadBalancer{@link com.netflix.loadbalancer.ILoadBalancer}-{@link com.netflix.loadbalancer.ZoneAwareLoadBalancer}
 * serverListUpdate{@link com.netflix.loadbalancer.ServerListUpdater}-{@link com.netflix.loadbalancer.PollingServerListUpdater}
 */

/**
 * 全局设置负载均衡策略
 */
//@Configuration
@AvoidScan
public class ClientConfig {
    @Autowired
    private IClientConfig clientConfig;


    /**
     * ribbon 轮询规则
     * random Rule 随机
     * roundRobinRule 循环轮询
     * retryRule 重试
     * bestAvailableRule 最低并发策略
     *
     * @return
     */
    @Bean
    public IRule ribbonRule(IClientConfig config) {
        //轮询
        //return new RoundRobinRule();
        //随机
        return new RandomRule();
        //重试
        //return new RetryRule();
        //最低并发策略
        //return new BestAvailableRule();
    }
}
