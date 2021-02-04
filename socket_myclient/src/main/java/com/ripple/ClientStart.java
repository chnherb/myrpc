package com.ripple;

import com.ripple.netty.NettyClient;
import com.ripple.service.HelloService;

public class ClientStart {

    public static void main(String[] args) throws Exception {

        //创建一个消费者
        NettyClient customer = new NettyClient();

        //创建代理对象
        HelloService service = (HelloService) customer.getBean(HelloService.class);
        for (int i = 1; i < 100; i++) {
            Thread.sleep(1 * 1000);
            String res = service.sayHello("hello world~" + i);
            System.out.println("res=" + res);
        }
    }
}
