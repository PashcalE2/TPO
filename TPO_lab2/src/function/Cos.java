package function;

import function.primitive.Sin;

public class Cos {
    public static double calc(double x, double accuracy) {
        return Sin.calc(x + Math.PI / 2, accuracy);
    }
}
