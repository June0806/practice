package com.june.practice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.util.List;

@RestController
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${spring.application.name}")
    private String serviceName;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/")
    public void index() throws MalformedURLException {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        List<String> services = discoveryClient.getServices();
        services.forEach(i -> {
            logger.info("service list:{}", i);
        });
        for (ServiceInstance instance : instances) {
            logger.info("host:{}", instance.getHost());
            instance.getMetadata().forEach((k, v) -> {
                logger.info("metadata: key:{},value:{}", k, v);
            });
            logger.info("port:{}", instance.getPort());
            logger.info("scheme:{}", instance.getScheme());
            logger.info("service id:{}", instance.getServiceId());
            logger.info("url:{}", instance.getUri().toURL());
        }
    }
}
