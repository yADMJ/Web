package repository;
import model.Paciente;
import model.Consulta;

import java.util.List;

public interface HistoricoConsultas {
    void adicionarConsulta(Paciente paciente, Consulta consulta);
    List<Consulta> buscarConsultasPorPaciente(Paciente paciente);
}