package com.ripple.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Invocation implements Serializable {
    private String interfaceName; //接口名
    private String methodName; // 方法名
    private Object[] params;  // 参数值列表
    private Class[] paramTypes; // 参数类型定列表

    public Invocation(String interfaceName, String methodName, Object[] params, Class[] paramTypes) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.params = params;
        this.paramTypes = paramTypes;
    }
}

