package com.ripple;

import com.ripple.client.HttpClient;
import com.ripple.service.HelloService;

public class ClientStart {
    public static void main(String[] args) throws Exception {
        HttpClient httpClient = new HttpClient();
        HelloService helloService = (HelloService) httpClient.getBean(HelloService.class);
        String result = helloService.sayHello("this is RPC~~~~~");
        System.out.println(result);
    }
}
