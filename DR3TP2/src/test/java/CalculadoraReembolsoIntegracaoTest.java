import model.Consulta;
import model.Paciente;
import service.AutorizadorReembolso;
import service.CalculadoraReembolso;
import model.PlanoSaude;
import testhelper.ConsultaHelper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraReembolsoIntegracaoTest {

    private final Paciente dummyPaciente = new Paciente();

    @Test
    public void deveCalcularReembolsoCompletoComStubMockEHelper() {
        PlanoSaude planoStub = () -> 0.8;

        AutorizadorReembolso autorizadorMock = Mockito.mock(AutorizadorReembolso.class);
        Mockito.when(autorizadorMock.estaAutorizado(Mockito.any(), Mockito.any())).thenReturn(true);

        Consulta consulta = ConsultaHelper.criarConsultaPadrao();

        CalculadoraReembolso calculadora = new CalculadoraReembolso(autorizadorMock);

        double reembolso = calculadora.calcular(consulta, dummyPaciente, planoStub);

        assertEquals(150.0, reembolso, 0.01);

        Mockito.verify(autorizadorMock, Mockito.times(1)).estaAutorizado(dummyPaciente, consulta);
    }
}
