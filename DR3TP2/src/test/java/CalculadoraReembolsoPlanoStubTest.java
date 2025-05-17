import model.Paciente;
import model.PlanoSaude;
import service.CalculadoraReembolso;
import testhelper.ConsultaHelper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraReembolsoPlanoStubTest {

    private final Paciente dummyPaciente = new Paciente();

    @Test
    public void deveCalcularComPlanoStub50Porcento() {
        PlanoSaude planoStub = () -> 0.5;
        CalculadoraReembolso calculadora = new CalculadoraReembolso((paciente, consulta) -> true);

        double valor = 200.0;
        double esperado = valor * 0.5;
        double reembolso = calculadora.calcular(valor, dummyPaciente, planoStub);

        assertEquals(esperado, reembolso);
    }
}
