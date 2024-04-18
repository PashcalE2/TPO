import function.*;
import function.primitive.Ln;
import function.primitive.Sin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.Function;

public class Main {
    private static final String csv_dir = "csv";

    private static void writeFunctionValues(String function_name, CalculableInterface function, double start, double finish, double step, double accuracy) {
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
                pw.println(String.format("%s,%s", Double.toString(start).replace(",", "."), Double.toString(function.calc(start, accuracy)).replace(",", ".")));
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
        writeFunctionValues("ln", new Ln(), 0, 10, 0.1, 0.0001);
        writeFunctionValues("log_5", new Log(5), 0, 10, 0.1, 0.0001);
        writeFunctionValues("log_10", new Log(10), 0, 10, 0.1, 0.0001);
        writeFunctionValues("sin", new Sin(), -Math.PI, Math.PI, Math.PI / 36, 0.0001);
        writeFunctionValues("cos", new Cos(), 0, Math.PI * 2, Math.PI / 36, 0.0001);
        writeFunctionValues("sec", new Sec(), -Math.PI * 2, Math.PI * 2, Math.PI / 36, 0.0001);
        writeFunctionValues("custom", new Custom(), -Math.PI * 2, Math.PI * 2, Math.PI / 36, 0.0001);
    }
}
