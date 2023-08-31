package org.grain;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.File;
import java.nio.file.Paths;

public class OneWayAuthServer {
    public static void main(String[] args) throws Exception {
        File certFile = Paths.get("certs", "server.crt").toFile();
        File keyFile = Paths.get("certs", "server.pem").toFile();
        Server server = ServerBuilder.forPort(8888)
                .addService(new ServiceImpl())
                .useTransportSecurity(certFile, keyFile)
                .build();
        server.start();
        Thread.sleep(100000);
        server.shutdown();
        System.out.println("end");
    }
}
