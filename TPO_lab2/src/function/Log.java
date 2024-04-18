package function;

import function.primitive.Ln;

public class Log implements CalculableInterface {
    private final Ln ln = new Ln();
    private final double base;

    public Log(double base) {
        this.base = base;
    }

    public double calc(double x, double accuracy) {
        return ln.calc(x, accuracy) / ln.calc(base, accuracy);
    }
}
