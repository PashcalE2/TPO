package Task1;

public class Cos {
    private static final double[] derivatives_at_zero_values =
            new double[] { 1.0, 0.0, -1.0, 0.0 };

    public static double[] derivativesAtZero(int order) {
        double[] derivatives = new double[order + 1];
        for (int i = 0; i < order; i++) {
            derivatives[i] = derivatives_at_zero_values[i & 3];
        }

        return derivatives;
    }
}
