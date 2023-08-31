package org.grain;


import io.grpc.stub.StreamObserver;

public class ServiceImpl extends HelloGrpc.HelloImplBase {
    @Override
    public void sayHello(Service.CheckReq request, StreamObserver<Service.Resp> responseObserver) {
        request.getMapMap().forEach((k, v) -> System.out.println(k + " " + v));
        System.out.println("complete");
        Service.Resp success = Service.Resp.newBuilder().clear().setCode(Service.StatusCode.SUCCESS).setDesc("success").build();
        responseObserver.onNext(success);
        responseObserver.onCompleted();
    }
}
