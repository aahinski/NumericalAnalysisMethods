import static java.lang.Math.sin;

public class FixedPointIteration {
    static double f(double x) {
        return (Math.pow(x, 2) + 4 * sin(x));
    }

    static double phi(double x) {
        return (x - Math.pow(x, 2) - 4 * sin(x));
    }

    static double algorithm(double x_null, double eps) {
        double x_K = x_null;
        double x_KPlusOne = phi(x_K);
        System.out.println(x_KPlusOne);
        int iterationsQuantity = 1;
        while(Math.abs(x_K - x_KPlusOne) > eps) {
            x_K = x_KPlusOne;
            x_KPlusOne = phi(x_K);
            iterationsQuantity++;
            //System.out.println(f(x_KPlusOne));
        }
        System.out.println(iterationsQuantity);
        return x_KPlusOne;
    }

    public static void main(String[] args) {
        System.out.println(algorithm(0.75, Math.pow(10, -7)));
        System.out.println(f(algorithm(-2.4, Math.pow(10, -7))));
    }
}
