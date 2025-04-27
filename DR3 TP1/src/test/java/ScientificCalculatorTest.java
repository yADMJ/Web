import org.example.ScientificCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ScientificCalculatorTest {

    private ScientificCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new ScientificCalculator();
    }

    @Test
    void testedeAdicao2numeros() {
        double result = calculator.add(5, 3);

        assertEquals(8, result);
    }

    @Test
    void testedeMenos2numeros() {
        double result = calculator.subtract(10, 4);

        assertEquals(6, result);
    }

    @Test
    void testedeMultiplacacao2numeros() {
        double result = calculator.squareRoot(16);

        assertEquals(4, result);
    }

    @Test
    void testedeMultiplicacaoNegativa2numeros() {
        assertThrows(IllegalArgumentException.class, () -> {

            calculator.squareRoot(-9);
        });
    }

    @Test
    void testeDividoporZero() {
        assertThrows(IllegalArgumentException.class, () -> {

            calculator.divide(5, 0);
        });
    }

    @Test
    void testedeLogPositivo2numeros() {
        double result = calculator.log(Math.E);

        assertEquals(1, result, 0.0001);
    }

    @Test
    void testeSin30degraus() {
        double result = calculator.sin(30);

        assertEquals(0.5, result, 0.0001);
    }

}
