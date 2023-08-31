package org.grain;

import io.grpc.ManagedChannel;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NettyChannelBuilder;
import io.netty.handler.ssl.SslContext;

import java.io.File;
import java.nio.file.Paths;

public class TwoWayAuthClient {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        File caFile = Paths.get("certs", "ca.crt").toFile();
        File certFile = Paths.get("certs", "client.crt").toFile();
        File keyFile = Paths.get("certs", "client.pem").toFile();
        SslContext sslContext = GrpcSslContexts.forClient().trustManager(caFile)
                .keyManager(certFile, keyFile).build();
        ManagedChannel channel = NettyChannelBuilder.forAddress("grain.org", 8888)
                .useTransportSecurity()
                .sslContext(sslContext)
                .build();
        Service.CheckReq build = Service.CheckReq.newBuilder().putMap("hi", "w").build();
        System.out.println("-------------------");
        Service.Resp resp = HelloGrpc.newBlockingStub(channel).sayHello(build);
        System.out.println(resp.getCode());
    }
}