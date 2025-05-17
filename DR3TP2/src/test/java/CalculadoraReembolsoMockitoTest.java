import model.Consulta;
import model.Paciente;
import service.AutorizadorReembolso;
import service.CalculadoraReembolso;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraReembolsoMockitoTest {

    private final Paciente dummyPaciente = new Paciente();

    @Test
    public void deveAplicarTetoDe150NoReembolso() {
        AutorizadorReembolso autorizadorMock = Mockito.mock(AutorizadorReembolso.class);
        Mockito.when(autorizadorMock.estaAutorizado(Mockito.any(), Mockito.any())).thenReturn(true);

        CalculadoraReembolso calculadora = new CalculadoraReembolso(autorizadorMock);

        Consulta consulta = new Consulta(LocalDate.now(), 300.0); // valor alto que geraria reembolso > 150
        double reembolso = calculadora.calcular(consulta, dummyPaciente, () -> 0.7);

        assertEquals(150.0, reembolso, 0.01);
    }
}
