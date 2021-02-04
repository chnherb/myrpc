package com.ripple.service.impl;

import com.ripple.service.HelloService;

public class HelloServiceImpl implements HelloService {
    public String sayHello(String msg) {
        return "service say hello: " + msg;
    }
}
