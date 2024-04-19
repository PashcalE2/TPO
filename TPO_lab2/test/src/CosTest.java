import function.Cos;
import function.primitive.Sin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class CosTest {
    private static final double accuracy = 0.0001;

    private final Sin mockSin = Mockito.mock(Sin.class);

    @Test
    @DisplayName("Проверка зависимости cos от sin")
    public void callSin() {
        final Cos cos = new Cos(mockSin);
        cos.calc(123, accuracy);

        verify(mockSin, atLeastOnce()).calc(any(Double.class), any(Double.class));
    }

    @Test
    @DisplayName("Расчет в первой четверти с помощью заглушки")
    public void calcWithMock() {
        double x = 0.1234;
        double sin = 0.123087;

        when(mockSin.calc(eq(x + Math.PI / 2), any(Double.class))).thenReturn(sin);

        Cos cos = new Cos(mockSin);
        assertEquals(Math.pow(1 - sin * sin, 0.5), cos.calc(x, accuracy), accuracy);
    }

    @ParameterizedTest(name = "cos({0}) = {1}")
    @DisplayName("Полная проверка периода")
    @CsvFileSource(resources = "/csv/expected/cos.csv", numLinesToSkip = 1, delimiter = ',')
    public void wholePeriod(double x, double answer) {
        final Cos cos = new Cos(new Sin());
        assertAll(
                () -> assertEquals(answer, cos.calc(x, accuracy), accuracy)
        );
    }
}