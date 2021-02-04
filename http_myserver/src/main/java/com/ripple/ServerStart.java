package com.ripple;

import com.ripple.pojo.URL;
import com.ripple.registry.NativeRegistry;
import com.ripple.service.HelloService;
import com.ripple.service.impl.HelloServiceImpl;
import com.ripple.tomcat.HttpServer;

public class ServerStart {
    public static void main(String[] args) {
        // 真正的注册服务
        URL url = new URL("localhost", 9000);
        NativeRegistry.regist(HelloService.class.getName(), url, HelloServiceImpl.class);
        //启动服务
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostName(), url.getPort());
    }
}
