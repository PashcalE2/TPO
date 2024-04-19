import function.Cos;
import function.Sec;
import function.primitive.Sin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class SecTest {
    private static final double accuracy = 0.0001;
    private final Cos mockCos = Mockito.mock(Cos.class);

    @Test
    @DisplayName("Проверка зависимости sec от cos")
    public void callCos() {
        final Sec sec = new Sec(mockCos);
        sec.calc(123, accuracy);

        verify(mockCos, atLeastOnce()).calc(any(Double.class), any(Double.class));
    }

    @Test
    @DisplayName("Расчет с помощью заглушки")
    public void calcWithMock() {
        double x = 0.1234;
        double cos = Math.cos(x);
        double sec_by_cos = 1 / cos;

        when(mockCos.calc(eq(x), any(Double.class))).thenReturn(cos);

        Sec sec = new Sec(mockCos);
        assertEquals(sec_by_cos, sec.calc(x, accuracy), accuracy);
    }

    @ParameterizedTest(name = "sec({0}) = {1}")
    @DisplayName("Полная проверка периода")
    @CsvFileSource(resources = "/csv/expected/sec.csv", numLinesToSkip = 1, delimiter = ',')
    public void wholePeriod(double x, double answer) {
        final Sec sec = new Sec(new Cos(new Sin()));
        assertAll(
                () -> assertEquals(answer, sec.calc(x, accuracy), accuracy)
        );
    }
}