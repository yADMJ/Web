package testhelper;

public class ComparadorDecimal {

    private static final double MARGEM_ERRO = 0.01;

    public static boolean iguaisComMargemErro(double esperado, double atual) {
        return Math.abs(esperado - atual) <= MARGEM_ERRO;
    }
}
