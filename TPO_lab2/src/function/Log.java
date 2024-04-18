package function;

import function.primitive.Ln;

public class Log {
    public static double calc(double x, double base, double accuracy) {
        return Ln.calc(x, accuracy) / Ln.calc(base, accuracy);
    }
}
