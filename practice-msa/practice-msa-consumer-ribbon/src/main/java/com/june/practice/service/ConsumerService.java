package com.june.practice.service;

import java.util.concurrent.ExecutionException;

/**
 *
 */
public interface ConsumerService {
    String invokeHelloService() throws ExecutionException, InterruptedException;
}
