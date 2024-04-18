package function.primitive;

import function.CalculableInterface;

public class Ln implements CalculableInterface {
    public Ln() {
    }

    public double calc(double x, double accuracy) {
        if (x == 0) {
            return Double.NEGATIVE_INFINITY;
        }
        else if (x < 0) {
            return Double.NaN;
        }
        else if (x == 1) {
            return 0;
        }

        double result = 0;

        if (x < 1) {
            x -= 1;

            double last_result;
            double x_power = x;
            double sign = 1;
            int i = 1;

            do {
                last_result = result;
                result += sign * x_power / (double) i;

                x_power *= x;
                i++;
                sign = -sign;
            } while (Math.abs((result - last_result) / last_result) > accuracy);
        }
        else {
            int int_ln_x = 0;
            result = x;
            while (result > 1) {
                result /= Math.E;
                int_ln_x++;
            }

            result = calc(result, accuracy) + (double) int_ln_x;
        }

        return result;
    }
}

/*

ln (x) = sum[n=1, inf] { (-1)^(n - 1) * (x - a)^n / (n * a^n) } + ln (a)

*/