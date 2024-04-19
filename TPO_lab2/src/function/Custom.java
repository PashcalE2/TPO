package function;

import function.primitive.Ln;

public class Custom implements CalculableInterface {
    private final Sec sec;
    private final Ln ln;
    private final Log log_5;
    private final Log log_10;


    public Custom(Sec sec, Ln ln, Log log_5, Log log_10){
        this.sec = sec;
        this.ln = ln;
        this.log_5 = log_5;
        this.log_10 = log_10;
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
