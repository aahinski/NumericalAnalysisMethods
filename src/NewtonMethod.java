public class NewtonMethod {
    static double f(double x) {
        return (Math.pow((x - 1), 2) - (0.5 * Math.exp(x)));
    }

    static double derivativeOfF(double x) {
        return ((2 * x) - 2 - 0.5 * Math.exp(x));
    }

    static double algorithm(double x_null, double eps) {
        double x_K = x_null;
        double x_KPlusOne = x_K - (f(x_K)/derivativeOfF(x_K));
        int iterationsQuantity = 1;
        while(Math.abs(x_K - x_KPlusOne) > eps) {
            x_K = x_KPlusOne;
            x_KPlusOne = x_K - (f(x_K)/derivativeOfF(x_K));
            iterationsQuantity++;
        }
        System.out.println(iterationsQuantity);
        return x_KPlusOne;
    }

    public static void main(String[] args) {
        System.out.println(algorithm(0.25, Math.pow(10, -7)));
        System.out.println(f(algorithm(0.25, Math.pow(10, -7))));
    }
}
