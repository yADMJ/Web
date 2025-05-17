package testhelper;

import model.Consulta;
import java.time.LocalDate;

public class ConsultaHelper {

    public static Consulta criarConsultaPadrao() {
        return new Consulta(LocalDate.now(), 200.0);
    }

    public static Consulta criarConsulta(double valor) {
        return new Consulta(LocalDate.now(), valor);
    }
}