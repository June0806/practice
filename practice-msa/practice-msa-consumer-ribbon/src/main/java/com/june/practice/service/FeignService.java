package com.june.practice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "hello-service")
public interface FeignService {
    @GetMapping("index")
    String invoke(@RequestParam("a") int a, @RequestParam("b") int b);
}
