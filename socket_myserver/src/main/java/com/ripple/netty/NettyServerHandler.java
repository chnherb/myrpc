package com.ripple.netty;

import com.ripple.service.impl.HelloServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

//服务器这边handler
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private final static String PROVIDER_NAME = "HelloService#sayHello";
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //获取客户端发送的消息，并调用服务
        System.out.println("receive msg = " + msg);
        //客户端在调用服务器的api 时，我们需要定义一个协议
        //比如我们要求 每次发消息是都必须以某个字符串开头 "HelloService#hello#你好"，相当于有多个服务或者接口时进行区分
        if(msg.toString().startsWith(PROVIDER_NAME)) {
            String result = new HelloServiceImpl().sayHello(msg.toString().substring(msg.toString().lastIndexOf("#") + 1));
            ctx.writeAndFlush(result);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
