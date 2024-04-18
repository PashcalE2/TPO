import function.Custom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomTest {
    private final double accuracy = 0.0001;

    @ParameterizedTest(name = "custom({0}) = {1}")
    @DisplayName("Проверки между (-pi/2; +pi/2)")
    @CsvFileSource(resources = "/csv/custom.csv", numLinesToSkip = 1, delimiter = ',')
    public void checkBetweenPI(double x, double custom) {
        assertAll(
                () -> assertEquals(custom, new Custom().calc(x, accuracy), accuracy)
        );
    }
}