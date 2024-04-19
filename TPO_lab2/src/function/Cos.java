package function;

import function.primitive.Sin;

public class Cos implements CalculableInterface {
    private final Sin sin;

    public Cos(Sin sin) {
        this.sin = sin;
    }

    public double calc(double x, double accuracy) {
        return sin.calc(x + Math.PI / 2, accuracy);
    }
}
