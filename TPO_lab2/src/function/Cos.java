package function;

import function.primitive.Sin;

public class Cos implements CalculableInterface {
    private final Sin sin = new Sin();

    public Cos() {
    }

    public double calc(double x, double accuracy) {
        return sin.calc(x + Math.PI / 2, accuracy);
    }
}
