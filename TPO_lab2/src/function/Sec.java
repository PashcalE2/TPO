package function;

public class Sec implements CalculableInterface {
    private final Cos cos;

    public Sec(Cos cos) {
        this.cos = cos;
    }

    public double calc(double x, double accuracy) {
        return 1 / cos.calc(x, accuracy);
    }
}
