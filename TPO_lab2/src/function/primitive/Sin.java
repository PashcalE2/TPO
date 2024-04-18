package function.primitive;

public class Sin {
    public static double calc(double x, double accuracy) {
        while (x < -Math.PI) {
            x += 2 * Math.PI;
        }

        while (x > Math.PI) {
            x -= 2 * Math.PI;
        }

        double result = 0;
        double last_result;
        double x_power = x;
        double sign = 1;
        double factorial = 1;
        int i = 1;

        do {
            last_result = result;
            result += sign * x_power / factorial;

            x_power *= x * x;
            i += 2;
            factorial *= (i - 1) * i;
            sign = -sign;
        } while (Math.abs((result - last_result) / last_result) > accuracy);

        return result;
    }
}

/*
sin => cos => -sin => -cos => sin

f(x) = sin(x) =
0 + 1/1! * x - 1/3! * x ^ 3 + 1/5! * x^5


*/