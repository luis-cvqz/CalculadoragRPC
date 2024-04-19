package calculadoragrpc.cliente;

import javax.swing.JOptionPane;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import com.proto.calculadora.CalculadoraServiceGrpc;
import com.proto.calculadora.Calculadora.MulRequest;
import com.proto.calculadora.Calculadora.MulResponse;
import com.proto.calculadora.Calculadora.DivRequest;
import com.proto.calculadora.Calculadora.DivResponse;
import com.proto.calculadora.Calculadora.SumRequest;
import com.proto.calculadora.Calculadora.SumResponse;
import com.proto.calculadora.Calculadora.ResRequest;
import com.proto.calculadora.Calculadora.ResResponse;

public class Cliente {
    public static void main(String[] args) {
        int puerto = 7878;
        String host = "localhost";

        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, puerto).usePlaintext().build();
        CalculadoraServiceGrpc.CalculadoraServiceBlockingStub stub = CalculadoraServiceGrpc.newBlockingStub(channel);

        while (true) {
            String opt = JOptionPane.showInputDialog(
                """
                    Calcular
                    (1) -> Suma
                    (2) -> Resta
                    (3) -> Multiplicación
                    (4) -> División
                    Cancelar para salir"""
            );

            if (opt == null) {
                break;
            }

            int a = Integer.parseInt(JOptionPane.showInputDialog("Ingrese a"));
            int b = Integer.parseInt(JOptionPane.showInputDialog("Ingrese b"));

            switch (opt) {
                case "1":
                    SumRequest sumRequest = SumRequest.newBuilder().setA(a).setB(b).build();
                    SumResponse sumResponse = stub.sumar(sumRequest);
                    JOptionPane.showMessageDialog(null, a + " + " + b + " = " + sumResponse.getRes() );
                    break;
                case "2":
                    ResRequest resRequest = ResRequest.newBuilder().setA(a).setB(b).build();
                    ResResponse resResponse = stub.restar(resRequest);
                    JOptionPane.showMessageDialog(null, a + " - " + b + " = " + resResponse.getRes() );
                    break;
                case "3":
                    MulRequest mulRequest = MulRequest.newBuilder().setA(a).setB(b).build();
                    MulResponse mulResponse = stub.multiplicar(mulRequest);
                    JOptionPane.showMessageDialog(null, a + " * " + b + " = " + mulResponse.getRes() );
                    break;
                case "4":
                    DivRequest divRequest = DivRequest.newBuilder().setA(a).setB(b).build();
                    DivResponse divResponse = stub.dividir(divRequest);
                    JOptionPane.showMessageDialog(null, a + " / " + b + " = " + divResponse.getRes() );
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Ingrese una opción válida.");
                    break;
            }
        }
    }
}
