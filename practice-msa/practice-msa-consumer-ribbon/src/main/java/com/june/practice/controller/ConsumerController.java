package com.june.practice.controller;

import com.june.practice.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;

    @GetMapping("consumer")
    public String consumer() {
        try {
            return consumerService.invokeHelloService();
        } catch (Exception ex) {
            return "发生异常:" + ex.getMessage();
        }
    }
}
