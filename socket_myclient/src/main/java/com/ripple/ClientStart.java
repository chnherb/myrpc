package com.ripple;

import com.ripple.netty.NettyClient;
import com.ripple.service.HelloService;

public class ClientStart {


    //这里定义协议头
    public static final String providerName = "HelloService#sayHello#";

    public static void main(String[] args) throws Exception {

        //创建一个消费者
        NettyClient customer = new NettyClient();

        //创建代理对象
        HelloService service = (HelloService) customer.getBean(HelloService.class, providerName);

        for (; ; ) {
            Thread.sleep(2 * 1000);
            //通过代理对象调用服务提供者的方法(服务)
            String res = service.sayHello("hello dubbo~");
            System.out.println("调用的结果 res= " + res);
        }
    }
}
