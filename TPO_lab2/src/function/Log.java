package function;

import function.primitive.Ln;

public class Log implements CalculableInterface {
    private final Ln ln;
    private final double base;

    public Log(Ln ln, double base) {
        this.ln = ln;
        this.base = base;
    }

    public double calc(double x, double accuracy) {
        return ln.calc(x, accuracy) / ln.calc(base, accuracy);
    }
}
