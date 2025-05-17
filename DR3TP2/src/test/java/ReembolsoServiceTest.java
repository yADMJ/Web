import service.ReembolsoService;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReembolsoServiceTest {

    @Test
    public void deveCalcularReembolsoBasico() {
        ReembolsoService service = new ReembolsoService();
        double valorConsulta = 200.0;
        double percentualCobertura = 0.70;

        double reembolso = service.calcularReembolso(valorConsulta, percentualCobertura);

        assertEquals(140.0, reembolso);
    }

    @Test
    public void deveRetornarZeroQuandoValorConsultaZero() {
        ReembolsoService service = new ReembolsoService();
        double valorConsulta = 0.0;
        double percentualCobertura = 0.70;

        double reembolso = service.calcularReembolso(valorConsulta, percentualCobertura);

        assertEquals(0.0, reembolso);
    }

    @Test
    public void deveRetornarZeroQuandoCoberturaZero() {
        ReembolsoService service = new ReembolsoService();
        double valorConsulta = 200.0;
        double percentualCobertura = 0.0;

        double reembolso = service.calcularReembolso(valorConsulta, percentualCobertura);

        assertEquals(0.0, reembolso);
    }

    @Test
    public void deveRetornarValorIntegralQuandoCoberturaCemPorcento() {
        ReembolsoService service = new ReembolsoService();
        double valorConsulta = 200.0;
        double percentualCobertura = 1.0;

        double reembolso = service.calcularReembolso(valorConsulta, percentualCobertura);

        assertEquals(200.0, reembolso);
    }
}