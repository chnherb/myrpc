package com.ripple.client;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpClient {
    //创建线程池
    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public Object getBean(final Class<?> serivceClass) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class<?>[]{serivceClass}, (proxy, method, args) -> {

                    HttpHandler httpHandler = new HttpHandler();
                    httpHandler.setClassName(serivceClass.getName());
                    httpHandler.setMethodName(method.getName());
                    httpHandler.setParamTypes(method.getParameterTypes());
                    httpHandler.setParamValues(args);
                    //设置要发给服务器端的信息
                    //providerName 协议头 args[0] 就是客户端调用api sayHello(???), 参数
                    return executor.submit(httpHandler).get();
                });
    }
}


