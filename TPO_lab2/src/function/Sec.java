package function;

public class Sec implements CalculableInterface {
    private final Cos cos = new Cos();

    public Sec() {
    }

    public double calc(double x, double accuracy) {
        return 1 / cos.calc(x, accuracy);
    }
}
