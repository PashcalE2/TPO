package function.primitive;

public class Ln {
    public static double[] derivativesAtZero(int order) {
        double[] derivatives = new double[order + 1];
        double factorial = 1;

        for (int i = 0; i < order; i++) {
            derivatives[i] = derivatives_at_zero_values[i & 3];
            factorial *= i + 1;
        }

        return derivatives;
    }
}
