package model;
import java.time.LocalDate;

public class Consulta {
    private LocalDate data;
    private double valor;

    public Consulta(LocalDate data, double valor) {
        this.data = data;
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }
}
