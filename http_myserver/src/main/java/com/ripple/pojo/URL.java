package com.ripple.pojo;

import lombok.Data;

@Data
public class URL {
    private String hostName;
    private Integer port;
    public URL(String hostName, Integer port) {
        this.hostName = hostName;
        this.port = port;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof URL)) {
            return false;
        }
        URL url = (URL) obj;
        if (hostName.equals(((URL) obj).getHostName()) && port.intValue() == url.port.intValue()) {
            return true;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return hostName.hashCode();
    }
}
