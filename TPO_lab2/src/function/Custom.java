package function;

import function.primitive.Ln;

public class Custom {
    public static double calc(double x, double accuracy) {
        if (x <= 0) {
            return Sec.calc(x, accuracy) / Sec.calc(x, accuracy);
        }

        double ln = Ln.calc(x, accuracy);
        double log_5 = Log.calc(x, 5, accuracy);
        double log_10 = Log.calc(x, 10, accuracy);

        return Math.pow((((ln / ln) / log_10) - log_5) * (Math.pow(log_5, 2)), 3);
    }
}
