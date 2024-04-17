package Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecByCosTest {
    private static double changeArg(double x) {
        while (x > Math.PI) {
            x -= 2 * Math.PI;
        }

        while (x < -Math.PI) {
            x += 2 * Math.PI;
        }

        return x;
    }

    private static final double delta = 0.001;
    private static final int derivativesOrder = 100;
    private static final double accuracy = 0.001;

    @ParameterizedTest(name = "sec({0})")
    @DisplayName("Проверки вокруг точек разрыва")
    @ValueSource(doubles = {
            -3 * Math.PI / 2 - delta,
            -3 * Math.PI / 2,
            -3 * Math.PI / 2 + delta,
            -Math.PI / 2 - delta,
            -Math.PI / 2,
            -Math.PI / 2 + delta,
            Math.PI / 2 - delta,
            Math.PI / 2,
            Math.PI / 2 + delta,
            3 * Math.PI / 2 - delta,
            3 * Math.PI / 2,
            3 * Math.PI / 2 + delta
    })
    public void checkPI(double x) {
        final double corrected_x = changeArg(x);
        assertAll(() -> assertEquals(1 / Math.cos(corrected_x), 1 / MaclaurinSeries.calc(corrected_x, Cos.derivativesAtZero(derivativesOrder)), accuracy));
    }

    @ParameterizedTest(name = "sec({0}) = {1}")
    @DisplayName("Проверки между (-pi/2; +pi/2)")
    @CsvFileSource(resources = "/test_values.csv", numLinesToSkip = 1, delimiter = ',')
    public void checkBetweenPI(double x, double sec) {
        final double corrected_x = changeArg(x);
        assertAll(
                () -> assertEquals(sec, 1 / MaclaurinSeries.calc(corrected_x, Cos.derivativesAtZero(derivativesOrder)), accuracy)
        );
    }
}