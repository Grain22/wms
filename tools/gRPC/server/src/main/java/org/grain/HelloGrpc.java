package org.grain;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 *
 */
@javax.annotation.Generated(value = "by gRPC proto compiler (version 1.56.0)", comments = "Source: service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class HelloGrpc {

    public static final String SERVICE_NAME = "Hello";
    private static final int METHODID_SAY_HELLO = 0;
    // Static method descriptors that strictly reflect the proto.
    private static volatile io.grpc.MethodDescriptor<Service.CheckReq, Service.Resp> getSayHelloMethod;
    private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

    private HelloGrpc() {
    }

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "sayHello", requestType = Service.CheckReq.class, responseType = Service.Resp.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<Service.CheckReq, Service.Resp> getSayHelloMethod() {
        io.grpc.MethodDescriptor<Service.CheckReq, Service.Resp> getSayHelloMethod;
        if ((getSayHelloMethod = HelloGrpc.getSayHelloMethod) == null) {
            synchronized (HelloGrpc.class) {
                if ((getSayHelloMethod = HelloGrpc.getSayHelloMethod) == null) {
                    HelloGrpc.getSayHelloMethod = getSayHelloMethod = io.grpc.MethodDescriptor.<Service.CheckReq, Service.Resp>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "sayHello")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(Service.CheckReq.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(Service.Resp.getDefaultInstance())).setSchemaDescriptor(new HelloMethodDescriptorSupplier("sayHello")).build();
                }
            }
        }
        return getSayHelloMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static HelloStub newStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<HelloStub> factory = new io.grpc.stub.AbstractStub.StubFactory<HelloStub>() {
            @Override
            public HelloStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new HelloStub(channel, callOptions);
            }
        };
        return HelloStub.newStub(factory, channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static HelloBlockingStub newBlockingStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<HelloBlockingStub> factory = new io.grpc.stub.AbstractStub.StubFactory<HelloBlockingStub>() {
            @Override
            public HelloBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new HelloBlockingStub(channel, callOptions);
            }
        };
        return HelloBlockingStub.newStub(factory, channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static HelloFutureStub newFutureStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<HelloFutureStub> factory = new io.grpc.stub.AbstractStub.StubFactory<HelloFutureStub>() {
            @Override
            public HelloFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new HelloFutureStub(channel, callOptions);
            }
        };
        return HelloFutureStub.newStub(factory, channel);
    }

    public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
        return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor()).addMethod(getSayHelloMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<Service.CheckReq, Service.Resp>(service, METHODID_SAY_HELLO))).build();
    }

    public static io.grpc.ServiceDescriptor getServiceDescriptor() {
        io.grpc.ServiceDescriptor result = serviceDescriptor;
        if (result == null) {
            synchronized (HelloGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new HelloFileDescriptorSupplier()).addMethod(getSayHelloMethod()).build();
                }
            }
        }
        return result;
    }

    /**
     *
     */
    public interface AsyncService {

        /**
         *
         */
        default void sayHello(Service.CheckReq request, io.grpc.stub.StreamObserver<Service.Resp> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSayHelloMethod(), responseObserver);
        }
    }

    /**
     * Base class for the server implementation of the service Hello.
     */
    public static abstract class HelloImplBase implements io.grpc.BindableService, AsyncService {

        @Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return HelloGrpc.bindService(this);
        }
    }

    /**
     * A stub to allow clients to do asynchronous rpc calls to service Hello.
     */
    public static final class HelloStub extends io.grpc.stub.AbstractAsyncStub<HelloStub> {
        private HelloStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected HelloStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new HelloStub(channel, callOptions);
        }

        /**
         *
         */
        public void sayHello(Service.CheckReq request, io.grpc.stub.StreamObserver<Service.Resp> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getSayHelloMethod(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     * A stub to allow clients to do synchronous rpc calls to service Hello.
     */
    public static final class HelloBlockingStub extends io.grpc.stub.AbstractBlockingStub<HelloBlockingStub> {
        private HelloBlockingStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected HelloBlockingStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new HelloBlockingStub(channel, callOptions);
        }

        /**
         *
         */
        public Service.Resp sayHello(Service.CheckReq request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getSayHelloMethod(), getCallOptions(), request);
        }
    }

    /**
     * A stub to allow clients to do ListenableFuture-style rpc calls to service Hello.
     */
    public static final class HelloFutureStub extends io.grpc.stub.AbstractFutureStub<HelloFutureStub> {
        private HelloFutureStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected HelloFutureStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new HelloFutureStub(channel, callOptions);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<Service.Resp> sayHello(Service.CheckReq request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getSayHelloMethod(), getCallOptions()), request);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>, io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final AsyncService serviceImpl;
        private final int methodId;

        MethodHandlers(AsyncService serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_SAY_HELLO:
                    serviceImpl.sayHello((Service.CheckReq) request, (io.grpc.stub.StreamObserver<Service.Resp>) responseObserver);
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new AssertionError();
            }
        }
    }

    private static abstract class HelloBaseDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
        HelloBaseDescriptorSupplier() {
        }

        @Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return Service.getDescriptor();
        }

        @Override
        public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("Hello");
        }
    }

    private static final class HelloFileDescriptorSupplier extends HelloBaseDescriptorSupplier {
        HelloFileDescriptorSupplier() {
        }
    }

    private static final class HelloMethodDescriptorSupplier extends HelloBaseDescriptorSupplier implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
        private final String methodName;

        HelloMethodDescriptorSupplier(String methodName) {
            this.methodName = methodName;
        }

        @Override
        public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(methodName);
        }
    }
}
