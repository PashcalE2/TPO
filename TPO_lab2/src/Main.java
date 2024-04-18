import function.Cos;
import function.Custom;
import function.Log;
import function.Sec;
import function.primitive.Ln;
import function.primitive.Sin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.function.Function;

public class Main {
    private static final String csv_dir = "csv";

    private static void writeFunctionValues(String function_name, Function<Double, Double> function, double start, double finish, double step) {
        if (finish < start) {
            throw new RuntimeException(String.format("finish (%f) < start (%f)", finish, start));
        }

        if (step <= 0) {
            throw new RuntimeException(String.format("step (%f) <= 0", step));
        }

        File csvFile = new File(csv_dir + "/" + function_name + ".csv");
        try (PrintWriter pw = new PrintWriter(csvFile)) {
            pw.println(String.format("x,%s", function_name + "(x)"));

            while (start < finish) {
                pw.println(String.format("%s,%s", Double.toString(start).replace(",", "."), Double.toString(function.apply(start)).replace(",", ".")));
                start += step;
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!csvFile.exists()) {
            throw new RuntimeException(String.format("Не удалось создать файл (%s)", function_name));
        }
    }

    public static void main(String[] args) {
        writeFunctionValues("ln", x -> Ln.calc(x, 0.0001), 0, 10, 0.1);
        writeFunctionValues("log_5", x -> Log.calc(x, 5, 0.0001), 0, 10, 0.1);
        writeFunctionValues("log_10", x -> Log.calc(x, 10, 0.0001), 0, 10, 0.1);
        writeFunctionValues("sin", x -> Sin.calc(x, 0.0001), -Math.PI * 10, Math.PI * 10, Math.PI / 36);
        writeFunctionValues("cos", x -> Cos.calc(x, 0.0001), -Math.PI * 10, Math.PI * 10, Math.PI / 36);
        writeFunctionValues("sec", x -> Sec.calc(x, 0.0001), -Math.PI * 10, Math.PI * 10, Math.PI / 36);
        writeFunctionValues("custom", x -> Custom.calc(x, 0.0001), -Math.PI * 10, Math.PI * 10, Math.PI / 36);
    }
}
