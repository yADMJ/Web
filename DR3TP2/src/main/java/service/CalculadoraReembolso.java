package service;

import model.Paciente;
import model.PlanoSaude;
import model.Consulta;

public class CalculadoraReembolso {

    private final AutorizadorReembolso autorizador;
    private static final double TETO_REEMBOLSO = 150.0;

    public CalculadoraReembolso(AutorizadorReembolso autorizador) {
        this.autorizador = autorizador;
    }

    public double calcular(Consulta consulta, Paciente paciente, PlanoSaude plano) {
        if (!autorizador.estaAutorizado(paciente, consulta)) {
            throw new IllegalStateException("Consulta não autorizada para reembolso.");
        }

        double percentual = plano.getPercentualCobertura();
        double reembolso = consulta.getValor() * percentual;

        if (reembolso > TETO_REEMBOLSO) {
            reembolso = TETO_REEMBOLSO;
        }

        return reembolso;
    }
}