package function;

import function.primitive.Ln;

public class Custom implements CalculableInterface {
    private final Sec sec = new Sec();
    private final Ln ln = new Ln();
    private final Log log_5 = new Log(5);
    private final Log log_10 = new Log(10);


    public Custom(){
    }

    public double calc(double x, double accuracy) {
        if (x <= 0) {
            return sec.calc(x, accuracy) / sec.calc(x, accuracy);
        }

        double ln_value = ln.calc(x, accuracy);
        double log_5_value = log_5.calc(x, accuracy);
        double log_10_value = log_10.calc(x, accuracy);

        return Math.pow((((ln_value / ln_value) / log_10_value) - log_5_value) * (Math.pow(log_5_value, 2)), 3);
    }
}
