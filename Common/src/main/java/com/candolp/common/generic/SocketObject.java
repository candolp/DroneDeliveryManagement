package com.candolp.common.generic;

public class SocketObject {
    private String IP;
    private int port;

    public SocketObject() {
    }

    public SocketObject(String socket) {
        this.IP = socket.trim().split("\\:")[0];
        this.port = Integer.parseInt(socket.trim().split("\\:")[1]);
    }

    public SocketObject(String IP, int port) {
        this.IP = IP;
        this.port = port;
    }

    public String getIP() {
        return this.IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String toString() {
        return "SocketObject{IP='" + this.IP + '\'' + ", port=" + this.port + '}';
    }
}
