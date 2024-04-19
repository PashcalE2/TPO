import function.primitive.Ln;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LnTest {
    private static final double accuracy = 0.0001;

    @ParameterizedTest(name = "ln({0}) = {1}")
    @DisplayName("Полная проверка периода")
    @CsvFileSource(resources = "/csv/expected/ln.csv", numLinesToSkip = 1, delimiter = ',')
    public void wholePeriod(double x, double answer) {
        final Ln ln = new Ln();
        assertAll(
                () -> assertEquals(answer, ln.calc(x, accuracy), accuracy)
        );
    }
}
