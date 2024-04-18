import function.Cos;
import function.Custom;
import function.Log;
import function.Sec;
import function.primitive.Ln;
import function.primitive.Sin;

public class Main {
    public static void main(String[] args) {
        System.out.println(Ln.calc(0.9, 0.0001));
        System.out.println(Custom.calc(1, 0.0001));
    }
}
