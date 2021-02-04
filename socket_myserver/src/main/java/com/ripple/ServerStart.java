package com.ripple;

import com.ripple.netty.NettyServer;

//ServerBootstrap 会启动一个服务提供者，就是 NettyServer
public class ServerStart {
    public static void main(String[] args) {

        //代码代填..
        NettyServer.startServer("127.0.0.1", 9000);
    }
}
