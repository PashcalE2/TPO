import function.Log;
import function.primitive.Ln;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class LogTest {
    private static final double accuracy = 0.0001;

    private final Ln mockLn = Mockito.mock(Ln.class);

    @Test
    @DisplayName("Проверка зависимости cos от sin")
    public void callLn() {
        final Log log = new Log(mockLn, 5);
        log.calc(123, accuracy);

        verify(mockLn, atLeastOnce()).calc(any(Double.class), any(Double.class));
    }

    @Test
    @DisplayName("Расчет логарифма с основаниями 5 и 10 с помощью натурального")
    public void calcWithMock() {
        double x = 12345;
        double ln = Math.log(x);
        double ln_5 = Math.log(5);
        double ln_10 = Math.log(10);
        double log_5_by_ln = ln / ln_5;
        double log_10_by_ln = ln / ln_10;

        when(mockLn.calc(eq(x), any(Double.class))).thenReturn(ln);
        when(mockLn.calc(eq(5d), any(Double.class))).thenReturn(ln_5);
        when(mockLn.calc(eq(10d), any(Double.class))).thenReturn(ln_10);

        Log log_5 = new Log(mockLn, 5d);
        assertEquals(log_5_by_ln, log_5.calc(x, accuracy), accuracy);

        Log log_10 = new Log(mockLn, 10d);
        assertEquals(log_10_by_ln, log_10.calc(x, accuracy), accuracy);
    }

    @ParameterizedTest(name = "log_5({0}) = {1}")
    @DisplayName("Полная проверка log_5")
    @CsvFileSource(resources = "/csv/expected/log_5.csv", numLinesToSkip = 1, delimiter = ',')
    public void log5Whole(double x, double answer) {
        final Log log_5 = new Log(new Ln(), 5);
        assertAll(
                () -> assertEquals(answer, log_5.calc(x, accuracy), accuracy)
        );
    }

    @ParameterizedTest(name = "log_10({0}) = {1}")
    @DisplayName("Полная проверка log_10")
    @CsvFileSource(resources = "/csv/expected/log_10.csv", numLinesToSkip = 1, delimiter = ',')
    public void log10Whole(double x, double answer) {
        final Log log_10 = new Log(new Ln(), 10);
        assertAll(
                () -> assertEquals(answer, log_10.calc(x, accuracy), accuracy)
        );
    }
}