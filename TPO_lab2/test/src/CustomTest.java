import function.Cos;
import function.Custom;
import function.Log;
import function.Sec;
import function.primitive.Ln;
import function.primitive.Sin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomTest {
    private final double accuracy = 0.0001;

    @ParameterizedTest(name = "custom({0}) = {1}")
    @DisplayName("Проверка непрерывных участков")
    @CsvFileSource(resources = "/csv/expected/custom.csv", numLinesToSkip = 1, delimiter = ',')
    public void checkContinuousPart(double x, double answer) {
        Ln ln = new Ln();
        Custom custom = new Custom(new Sec(new Cos(new Sin())), ln, new Log(ln, 5), new Log(ln, 10));
        assertAll(
                () -> assertEquals(answer, custom.calc(x, accuracy), accuracy)
        );
    }

    @ParameterizedTest(name = "custom({0}) = {1}")
    @DisplayName("Проверка точек разрыва при x <= 0")
    @CsvFileSource(resources = "/csv/expected/custom_sec_critical.csv", numLinesToSkip = 1, delimiter = ',')
    public void checkSecCriticalPoints(double x, double answer) {
        Ln ln = new Ln();
        Custom custom = new Custom(new Sec(new Cos(new Sin())), ln, new Log(ln, 5), new Log(ln, 10));
        assertAll(
                () -> assertEquals(answer, custom.calc(x, accuracy), accuracy)
        );
    }

    @ParameterizedTest(name = "custom({0}) = {1}")
    @DisplayName("Проверка точек разрыва при x > 0")
    @CsvFileSource(resources = "/csv/expected/custom_logs_critical.csv", numLinesToSkip = 1, delimiter = ',')
    public void checkLogsCriticalPoints(double x, double answer) {
        Ln ln = new Ln();
        Custom custom = new Custom(new Sec(new Cos(new Sin())), ln, new Log(ln, 5), new Log(ln, 10));
        assertAll(
                () -> assertEquals(answer, custom.calc(x, accuracy), accuracy)
        );
    }
}