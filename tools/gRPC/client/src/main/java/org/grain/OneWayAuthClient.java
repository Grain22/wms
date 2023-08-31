package org.grain;

import io.grpc.ManagedChannel;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NettyChannelBuilder;
import io.netty.handler.ssl.SslContext;

import java.io.File;
import java.nio.file.Paths;

public class OneWayAuthClient {
    public static void main(String[] args) throws Exception {
        File certFile = Paths.get("certs", "ca.crt").toFile();
        SslContext sslContext = GrpcSslContexts.forClient().trustManager(certFile).build();
        ManagedChannel channel = NettyChannelBuilder.forAddress("grain.org", 8888)
                .useTransportSecurity()
                .sslContext(sslContext)
                .build();
        Service.Resp resp = HelloGrpc.newBlockingStub(channel).sayHello(Service.CheckReq.newBuilder().putMap("a", "b").build());
        Service.StatusCode code = resp.getCode();
        System.out.println(code);
    }
}
