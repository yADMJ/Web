package service;

import model.Consulta;

public class AuditoriaSpy implements Auditoria {

    private boolean metodoChamado = false;
    private Consulta consultaRegistrada;

    @Override
    public void registrarConsulta(Consulta consulta) {
        metodoChamado = true;
        consultaRegistrada = consulta;
    }

    public boolean foiChamado() {
        return metodoChamado;
    }

    public Consulta getConsultaRegistrada() {
        return consultaRegistrada;
    }
}