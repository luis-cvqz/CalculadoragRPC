package calculadoragrpc.servidor;

import com.proto.calculadora.CalculadoraServiceGrpc;

import com.proto.calculadora.Calculadora.MulRequest;
import com.proto.calculadora.Calculadora.MulResponse;
import com.proto.calculadora.Calculadora.DivRequest;
import com.proto.calculadora.Calculadora.DivResponse;
import com.proto.calculadora.Calculadora.SumRequest;
import com.proto.calculadora.Calculadora.SumResponse;
import com.proto.calculadora.Calculadora.ResRequest;
import com.proto.calculadora.Calculadora.ResResponse;

import io.grpc.stub.StreamObserver;

public class ServidorImpl extends CalculadoraServiceGrpc.CalculadoraServiceImplBase {
    @Override
    public void multiplicar(MulRequest mulRequest, StreamObserver<MulResponse> responseStreamObserver) {
        MulResponse mulResponse = MulResponse.newBuilder().setRes(mulRequest.getA() * mulRequest.getB()).build();
        responseStreamObserver.onNext(mulResponse);
        responseStreamObserver.onCompleted();
    }

    @Override
    public void dividir(DivRequest divRequest, StreamObserver<DivResponse> requestStreamObserver) {
        DivResponse divResponse = DivResponse.newBuilder().setRes(divRequest.getA() / divRequest.getB()).build();
        requestStreamObserver.onNext(divResponse);
        requestStreamObserver.onCompleted();
    }

    @Override
    public void sumar(SumRequest sumRequest, StreamObserver<SumResponse> responseStreamObserver) {
        SumResponse sumResponse = SumResponse.newBuilder().setRes(sumRequest.getA() + sumRequest.getB()).build();
        responseStreamObserver.onNext(sumResponse);
        responseStreamObserver.onCompleted();
    }

    @Override
    public void restar(ResRequest resRequest, StreamObserver<ResResponse> requestStreamObserver) {
        ResResponse resResponse = ResResponse.newBuilder().setRes(resRequest.getA() - resRequest.getB()).build();
        requestStreamObserver.onNext(resResponse);
        requestStreamObserver.onCompleted();
    }
}
