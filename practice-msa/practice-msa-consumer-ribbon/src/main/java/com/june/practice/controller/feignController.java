package com.june.practice.controller;

import com.june.practice.service.FeignInvokeService;
import com.june.practice.utils.HystrixThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@RestController
@RequestMapping("feign")
public class feignController {
    @Autowired
    private FeignInvokeService feignService;

    /**
     * @param userId
     * @param a
     * @param b
     * @return
     */
    @GetMapping("/index/{id}")
    public String index(@PathVariable("id") Integer userId, Integer a, Integer b) {
        HystrixThreadLocal.ThreadLocal.set("UserId:" + userId);
        RequestContextHolder.currentRequestAttributes().setAttribute("id", userId, RequestAttributes.SCOPE_REQUEST);
        System.out.println("current thread:" + Thread.currentThread().getId());
        System.out.println("thread local:" + HystrixThreadLocal.ThreadLocal.get());
        System.out.println("HystrixThreadLocal:" + HystrixThreadLocal.ThreadLocal.get());
        int iA = a == null ? 0 : a.intValue();
        int iB = b == null ? 0 : b.intValue();
        return feignService.invoke(userId, a, b);
    }
}
