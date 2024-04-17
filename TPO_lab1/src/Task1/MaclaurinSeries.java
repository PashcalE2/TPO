package Task1;

public class MaclaurinSeries {
    public static double calc(double x, double[] derivatives) {
        double partialSum = 0;
        double factorial = 1;
        double xPower = 1;

        for (int i = 0; i < derivatives.length; i++) {
            partialSum += derivatives[i] / factorial * xPower;
            xPower *= x;
            factorial *= i + 1;
        }

        return partialSum;
    }
}
