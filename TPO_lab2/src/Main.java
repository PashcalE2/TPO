import function.*;
import function.primitive.Ln;
import function.primitive.Sin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.Function;

public class Main {
    private static final String csv_dir = "test/resources/csv";

    private static void writeFunctionValues(String subdir, String function_name, Function<Double, Double> function, double start, double finish, double step) {
        if (finish < start) {
            throw new RuntimeException(String.format("finish (%f) < start (%f)", finish, start));
        }

        if (step <= 0) {
            throw new RuntimeException(String.format("step (%f) <= 0", step));
        }

        File csvFile = new File(csv_dir + "/" + subdir + "/" + function_name + ".csv");
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

    private static double customFunction(double x) {
        if (x <= 0) {
            return (1 / Math.cos(x)) / (1 / Math.cos(x));
        }

        double ln_value = Math.log(x);
        double log_5_value = Math.log(x) / Math.log(5);
        double log_10_value = Math.log(x) / Math.log(10);

        return Math.pow((((ln_value / ln_value) / log_10_value) - log_5_value) * (Math.pow(log_5_value, 2)), 3);
    }

    public static void main(String[] args) {
        double accuracy = 0.0001;

        Ln ln = new Ln();
        writeFunctionValues("actual", "ln", (x) -> ln.calc(x, accuracy), 0, 10, 0.1);
        writeFunctionValues("expected", "ln", Math::log, 0, 10, 0.1);

        Log log_5 = new Log(ln, 5);
        writeFunctionValues("actual", "log_5", (x) -> log_5.calc(x, accuracy), 0, 10, 0.1);
        writeFunctionValues("expected", "log_5", (x) -> Math.log(x) / Math.log(5), 0, 10, 0.1);

        Log log_10 = new Log(ln, 10);
        writeFunctionValues("actual", "log_10", (x) -> log_10.calc(x, accuracy), 0, 10, 0.1);
        writeFunctionValues("expected", "log_10", (x) -> Math.log(x) / Math.log(10), 0, 10, 0.1);

        Sin sin = new Sin();
        writeFunctionValues("actual", "sin", (x) -> sin.calc(x, accuracy), -Math.PI, Math.PI, Math.PI / 36);
        writeFunctionValues("expected", "sin", Math::sin, -Math.PI, Math.PI, Math.PI / 36);

        Cos cos = new Cos(sin);
        writeFunctionValues("actual", "cos", (x) -> cos.calc(x, accuracy), 0, Math.PI * 2, Math.PI / 36);
        writeFunctionValues("expected", "cos", Math::cos, 0, Math.PI * 2, Math.PI / 36);

        Sec sec = new Sec(cos);
        writeFunctionValues("actual", "sec", (x) -> sec.calc(x, accuracy), -Math.PI * 2, Math.PI * 2, Math.PI / 36);
        writeFunctionValues("expected", "sec", (x) -> 1 / Math.cos(x), -Math.PI * 2, Math.PI * 2, Math.PI / 36);

        Custom custom = new Custom(sec, ln, log_5, log_10);
        writeFunctionValues("actual", "custom", (x) -> custom.calc(x, accuracy), -10, 10, (double) 1 / 32);
        writeFunctionValues("expected", "custom", Main::customFunction, -10, 10, (double) 1 / 32);

        writeFunctionValues("actual", "custom_sec_critical", (x) -> custom.calc(x, accuracy), -Math.PI * 2.5, Math.PI * 2.5, Math.PI / 2);
        writeFunctionValues("expected", "custom_sec_critical", Main::customFunction, -Math.PI * 2.5, Math.PI * 2.5, Math.PI / 2);

        writeFunctionValues("actual", "custom_logs_critical", (x) -> custom.calc(x, accuracy), 0, 2, (double) 1 / 32);
        writeFunctionValues("expected", "custom_logs_critical", Main::customFunction, 0, 2, (double) 1 / 32);

        // for plot
        writeFunctionValues("actual", "custom_for_plot", (x) -> custom.calc(x, accuracy), -10, 10, (double) 1 / 256);
    }
}
