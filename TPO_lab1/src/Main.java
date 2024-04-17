import Task1.Cos;
import Task1.MaclaurinSeries;

public class Main {
    public static void main(String[] args) {
        double x_value = Math.PI / 2 - 0.001;
        double[] derivatives = Cos.derivativesAtZero(200);
        System.out.println(1 / MaclaurinSeries.calc(x_value, derivatives));
        System.out.println(1 / Math.cos(x_value));
    }
}