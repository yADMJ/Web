import model.Paciente;
import service.AuditoriaSpy;
import service.CalculadoraReembolso;
import testhelper.ConsultaHelper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraReembolsoAuditoriaTest {

    private final Paciente dummyPaciente = new Paciente();

    @Test
    public void deveChamarAuditoriaAoRegistrarConsulta() {
        AuditoriaSpy auditoriaSpy = new AuditoriaSpy();
        CalculadoraReembolso calculadora = new CalculadoraReembolso(auditoriaSpy);

        double valor = 200.0;
        calculadora.calcular(valor, dummyPaciente, () -> 0.7);

        assertTrue(auditoriaSpy.foiChamado());
        assertEquals(valor, auditoriaSpy.getConsultaRegistrada().getValor());
    }
}
