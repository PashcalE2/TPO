package function;

import function.primitive.MaclaurinSeries;
import function.primitive.Sin;

public class Cos {
    public static double calc(double x, int order) {
        return MaclaurinSeries.calc(x + Math.PI / 2, Sin.derivativesAtZero(order));
    }
}
