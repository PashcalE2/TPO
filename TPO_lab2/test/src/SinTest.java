import function.primitive.Sin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SinTest {
    private static final double accuracy = 0.0001;

    @ParameterizedTest(name = "sin({0}) = {1}")
    @DisplayName("Полная проверка периода")
    @CsvFileSource(resources = "/csv/expected/sin.csv", numLinesToSkip = 1, delimiter = ',')
    public void wholePeriod(double x, double answer) {
        final Sin sin = new Sin();
        assertAll(
                () -> assertEquals(answer, sin.calc(x, accuracy), accuracy)
        );
    }


}
