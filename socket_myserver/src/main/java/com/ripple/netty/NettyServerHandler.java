package com.ripple.netty;

import com.alibaba.fastjson.JSON;
import com.ripple.pojo.Invocation;
import com.ripple.pojo.URL;
import com.ripple.registry.NativeRegistry;
import com.ripple.service.impl.HelloServiceImpl;
import com.ripple.util.GsonUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

//服务器这边handler
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //1、获取客户端发送的消息，并调用服务
        System.out.println("receive msg = " + msg);
        Invocation invocation = (Invocation) JSON.parseObject((String)msg, Invocation.class);

        // 2、从注册中心获取服务的列表
        Class implClass = NativeRegistry.get(invocation.getInterfaceName(), new URL("127.0.0.1", 9000));

        // 3、调用服务 反射
        Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
        String result = (String) method.invoke(implClass.newInstance(), invocation.getParams());

        ctx.writeAndFlush(result);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}

