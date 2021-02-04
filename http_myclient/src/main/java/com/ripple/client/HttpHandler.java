package com.ripple.client;

import com.ripple.pojo.Invocation;
import lombok.Data;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

@Data
public class HttpHandler implements Callable {
    private String className;
    private String methodName;
    private Object[] paramValues;
    private Class[] paramTypes;

    public String post(String hostName, int port) throws IOException {

        // 1. 进行连接
        URL url = new URL("http", hostName, port, "/client");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);
        // 2. 发送调用信息
        OutputStream outputStream = urlConnection.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        Invocation invocation = new Invocation(className, methodName, paramValues, paramTypes);
        objectOutputStream.writeObject(invocation);
        objectOutputStream.flush();
        objectOutputStream.close();
        // 3. 将输入流转换成字符串，获取远程调用的结果
        InputStream inputStream = urlConnection.getInputStream();
        return IOUtils.toString(inputStream);
    }

    public synchronized Object call() throws Exception {
        String result = post("localhost", 8080);
        return result;
    }

}
