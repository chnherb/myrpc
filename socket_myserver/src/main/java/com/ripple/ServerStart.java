package com.ripple;

import com.ripple.netty.NettyServer;
import com.ripple.pojo.URL;
import com.ripple.registry.NativeRegistry;
import com.ripple.service.HelloService;
import com.ripple.service.impl.HelloServiceImpl;

//ServerBootstrap 会启动一个服务提供者，就是 NettyServer
public class ServerStart {
    public static void main(String[] args) {

        URL url = new URL("127.0.0.1", 9000);
        NativeRegistry.regist(HelloService.class.getName(), url, HelloServiceImpl.class);

        //代码代填..
        NettyServer.startServer(url.getHostName(), url.getPort());
    }
}
