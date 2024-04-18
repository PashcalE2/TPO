package function;

public class Sec {
    public static double calc(double x, double accuracy) {
        return 1 / Cos.calc(x, accuracy);
    }
}
