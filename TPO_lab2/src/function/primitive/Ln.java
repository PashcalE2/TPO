package function.primitive;

public class Ln {
    public static double[] derivativesAtOne(int order) {
        double[] derivatives = new double[order + 1];
        double factorial = 1;

        derivatives[0] = 0;

        for (int i = 1; i < order; i++) {
            derivatives[i] = factorial;
            factorial *= -i;
        }

        return derivatives;
    }

    public static double calc(double x, int order) {
        x -= 1;
        double result = 0;
        double x_power = x;
        double sign = 1;

        for (int i = 1; i <= order; i++) {
            result += sign * x_power / i;
            sign = -sign;
            x_power *= x;
        }

        return result;
    }
}
