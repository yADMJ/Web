package repository;
import model.Paciente;
import model.Consulta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoricoConsultasFake implements HistoricoConsultas {


    private final Map<Paciente, List<Consulta>> dados = new HashMap<>();

    @Override
    public void adicionarConsulta(Paciente paciente, Consulta consulta) {
        dados.computeIfAbsent(paciente, k -> new ArrayList<>()).add(consulta);
    }

    @Override
    public List<Consulta> buscarConsultasPorPaciente(Paciente paciente) {
        return dados.getOrDefault(paciente, new ArrayList<>());
    }
}