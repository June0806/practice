package com.june.practice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.util.List;

@RestController
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${spring.application.name}")
    private String serviceName;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/index")
    public String index(int a, int b, HttpServletRequest request) throws MalformedURLException {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        List<String> services = discoveryClient.getServices();
        services.forEach(i -> {
            logger.info("service list:{}", i);
        });
        StringBuilder stringBuilder = new StringBuilder();
        for (ServiceInstance instance : instances) {
            stringBuilder.append("host:" + instance.getHost());
            instance.getMetadata().forEach((k, v) -> {
                stringBuilder.append("meta data: key:" + k + " value:" + v);
            });
            stringBuilder.append("port:" + instance.getPort());
            stringBuilder.append("scheme:" + instance.getScheme());
            stringBuilder.append("service id :" + instance.getServiceId());
            stringBuilder.append("url:" + instance.getUri().toURL());
        }
        return "from port :" + request.getServerPort() + "result :" + (a + b);
        //return stringBuilder.toString();
    }
}
