import function.Custom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomTest {
    private final double accuracy = 0.0001;

    @ParameterizedTest(name = "custom({0}) = {1}")
    @DisplayName("Проверка точек разрыва")
    @CsvFileSource(resources = "/csv/expected/custom.csv", numLinesToSkip = 1, delimiter = ',')
    public void checkCriticalPoints(double x, double answer) {
        Custom custom = new Custom();
        assertAll(
                () -> assertEquals(answer, custom.calc(x, accuracy), accuracy)
        );
    }

    @ParameterizedTest(name = "custom({0}) = {1}")
    @DisplayName("Проверка непрерывных участков")
    @CsvFileSource(resources = "/csv/expected/custom.csv", numLinesToSkip = 1, delimiter = ',')
    public void checkContinuousPart(double x, double custom) {

    }
}