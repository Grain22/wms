package net.socket.contants;

import tools.thread.CustomThreadPool;

import java.util.concurrent.ExecutorService;

public class Contants {
    public static final String END = "end";
    public static int POINT = 10000;
    public static String command = "///";
    public static ExecutorService socket_client;

    static {
        socket_client = CustomThreadPool.createThreadPool("socket_client");
    }
}
