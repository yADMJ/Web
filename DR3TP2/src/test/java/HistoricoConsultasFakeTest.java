import model.Paciente;
import model.Consulta;

import org.junit.jupiter.api.Test;
import repository.HistoricoConsultasFake;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HistoricoConsultasFakeTest {

    @Test
    public void deveAdicionarEBuscarConsultasPorPaciente() {
        HistoricoConsultasFake historico = new HistoricoConsultasFake();
        Paciente paciente = new Paciente();
        Consulta consulta1 = new Consulta(LocalDate.of(2025, 5, 10), 150.0);
        Consulta consulta2 = new Consulta(LocalDate.of(2025, 5, 12), 200.0);

        historico.adicionarConsulta(paciente, consulta1);
        historico.adicionarConsulta(paciente, consulta2);

        List<Consulta> consultas = historico.buscarConsultasPorPaciente(paciente);

        assertEquals(2, consultas.size());
        assertTrue(consultas.contains(consulta1));
        assertTrue(consultas.contains(consulta2));
    }

    @Test
    public void deveRetornarListaVaziaQuandoPacienteNaoTemConsultas() {
        HistoricoConsultasFake historico = new HistoricoConsultasFake();
        Paciente paciente = new Paciente();

        List<Consulta> consultas = historico.buscarConsultasPorPaciente(paciente);

        assertNotNull(consultas);
        assertTrue(consultas.isEmpty());
    }
}