package service;

import model.Paciente;
import model.Consulta;

public interface AutorizadorReembolso {
    boolean estaAutorizado(Paciente paciente, Consulta consulta);
}
