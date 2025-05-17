import model.Paciente;
import model.PlanoSaude;
import org.junit.jupiter.api.Test;
import service.CalculadoraReembolso;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraReembolsoTest {

    private final Paciente dummyPaciente = new Paciente();

    private final PlanoSaude plano50 = new PlanoSaude() {
        @Override
        public double getPercentualCobertura() {
            return 0.50;
        }
    };

    private final PlanoSaude plano80 = new PlanoSaude() {
        @Override
        public double getPercentualCobertura() {
            return 0.80;
        }
    };

    @Test
    public void deveCalcularReembolsoComPlano50() {
        CalculadoraReembolso calculadora = new CalculadoraReembolso();
        double valorConsulta = 200.0;

        double reembolso = calculadora.calcular(valorConsulta, dummyPaciente, plano50);

        assertEquals(100.0, reembolso);
    }

    @Test
    public void deveCalcularReembolsoComPlano80() {
        CalculadoraReembolso calculadora = new CalculadoraReembolso();
        double valorConsulta = 200.0;

        double reembolso = calculadora.calcular(valorConsulta, dummyPaciente, plano80);

        assertEquals(160.0, reembolso);
    }
}