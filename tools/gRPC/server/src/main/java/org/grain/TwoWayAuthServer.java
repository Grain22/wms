package org.grain;

import io.grpc.Server;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NettyServerBuilder;
import io.netty.handler.ssl.ClientAuth;
import io.netty.handler.ssl.SslContext;

import java.io.File;
import java.nio.file.Paths;

public class TwoWayAuthServer {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        File server_crt = Paths.get("certs", "server.crt").toFile();
        File server_pem = Paths.get("certs", "server.pem").toFile();
        File ca_crt = Paths.get("certs", "ca.crt").toFile();
        SslContext sslContext = GrpcSslContexts.forServer(server_crt, server_pem).trustManager(ca_crt).clientAuth(ClientAuth.REQUIRE).build();
        Server build = NettyServerBuilder.forPort(8888).addService(new ServiceImpl()).sslContext(sslContext).build();
        build.start();
        Thread.sleep(100000);
        build.shutdown();
        System.out.println("end");
    }
}