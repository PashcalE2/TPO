import function.Cos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CosTest {
    private static final double accuracy = 0.0001;

    @ParameterizedTest(name = "cos({0}) = {1}")
    @DisplayName("Полная проверка")
    @CsvFileSource(resources = "/csv/expected/cos.csv", numLinesToSkip = 1, delimiter = ',')
    void periodic(double x, double answer) {
        final Cos cos = new Cos();
        assertAll(
                () -> assertEquals(answer, cos.calc(x, accuracy), accuracy)
        );
    }
}